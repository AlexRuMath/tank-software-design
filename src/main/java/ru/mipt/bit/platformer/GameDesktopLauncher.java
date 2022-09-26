package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.builders.ILevelObstacleBuilder;
import ru.mipt.bit.platformer.builders.LevelObstacleBuilderDefault;
import ru.mipt.bit.platformer.controllers.IMoveController;
import ru.mipt.bit.platformer.controllers.DirectionMoveController;
import ru.mipt.bit.platformer.entity.*;
import ru.mipt.bit.platformer.level.ILevelObstacle;
import ru.mipt.bit.platformer.level.LevelRender;
import ru.mipt.bit.platformer.util.Direction;
import ru.mipt.bit.platformer.util.Transform;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class GameDesktopLauncher implements ApplicationListener {

    private static final float MOVEMENT_SPEED = 0.4f;

    private final LevelRender LevelRender = ru.mipt.bit.platformer.level.LevelRender.getInstance();

    private Batch batch;

    private TankEntity tankEntity;

    private IMoveController directionController;

    private ILevelObstacle levelObstacle;


    @Override
    public void create() {
        batch = new SpriteBatch();

        LevelRender.setLevelScheme("level.tmx");
        LevelRender.setLayerRenderer(batch);
        LevelRender.setLayerMovement("Ground", Interpolation.smooth);

        ILevelObstacleBuilder obstacleBuilder = new LevelObstacleBuilderDefault();
        obstacleBuilder.addTree(new GridPoint2(5, 5));
        obstacleBuilder.addTree(new GridPoint2(2, 1));
        obstacleBuilder.addTree(new GridPoint2(6, 2));
        levelObstacle = obstacleBuilder.create();

        LevelRender.setObstacle(levelObstacle);

        ModelTexture tankTexture = new ModelTexture("images/tank_blue.png");
        Transform tankTransform = new Transform(new GridPoint2(1, 1), 0f);
        tankEntity = new TankEntity(tankTransform, tankTexture);
        directionController = new DirectionMoveController();
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        clearScreen();

        Direction direction = directionController.getDirection(Gdx.input);

        calculateDestinationPosition(tankEntity, direction);

        LevelRender.mapMovements.get("Ground")
                .moveRectangleBetweenTileCenters(tankEntity.modelTexture.getRectangle(), tankEntity);

        interpolatedPosition(deltaTime);

        // render each tile of the level
        LevelRender.render();

        // start recording all drawing commands
        batch.begin();

        // render player
        drawModel(tankEntity.modelTexture, tankEntity.transform);

        // render obstacle
        for (BaseEntity entity : levelObstacle.getEntities()) drawModel(entity.modelTexture, entity.transform);

        // submit all drawing requests
        batch.end();
    }

    private void calculateDestinationPosition(IMoveEntity moveEntity, Direction direction) {
        if (!isEqual(moveEntity.getMovementProgress(), 1f)) return;

        Transform newTransform = direction.stepInTheDirection(moveEntity.getTransform());
        boolean isObstaclePosition = levelObstacle.getPositions().contains(newTransform.getPosition());

        if (!isObstaclePosition) {
            moveEntity.setDestinationTransform(newTransform);
        }
    }

    private void drawModel(ModelTexture graphic, Transform transform) {
        drawTextureRegionUnscaled(batch, graphic.getTextureRegion(), graphic.getRectangle(), transform.getRotation());
    }

    private void interpolatedPosition(float deltaTime) {
        float newProgress = continueProgress(tankEntity.getMovementProgress(), deltaTime, MOVEMENT_SPEED);
        tankEntity.setMovementProgress(newProgress);
        if (isEqual(tankEntity.getMovementProgress(), 1f)) {
            tankEntity.setDestinationPositionAsPosition();
        }
    }

    private static void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        for (BaseEntity entity : levelObstacle.getEntities()) {
            entity.modelTexture.getTexture().dispose();
        }

        tankEntity.modelTexture.getTexture().dispose();
        LevelRender.tiledMap.dispose();
        batch.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
