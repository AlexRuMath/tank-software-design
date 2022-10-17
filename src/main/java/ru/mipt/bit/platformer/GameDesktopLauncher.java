package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.gameobjects.IGameObject;
import ru.mipt.bit.platformer.generators.LevelDataFromFile;
import ru.mipt.bit.platformer.controllers.IMoveController;
import ru.mipt.bit.platformer.controllers.DirectionMoveController;
import ru.mipt.bit.platformer.entity.*;
import ru.mipt.bit.platformer.level.DTO.IDataLevel;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.level.LevelRender;
import ru.mipt.bit.platformer.parser.IParser;
import ru.mipt.bit.platformer.parser.ParseResult;
import ru.mipt.bit.platformer.parser.ParserLevelFromTxt;
import ru.mipt.bit.platformer.util.Direction;
import ru.mipt.bit.platformer.util.Transform;

import java.util.Random;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class GameDesktopLauncher implements ApplicationListener {

    private static final float MOVEMENT_SPEED = 0.4f;

    private final LevelRender LevelRender = ru.mipt.bit.platformer.level.LevelRender.getInstance();

    private Batch batch;

    private IMoveController directionController;

    private Level level;

    @Override
    public void create() {
        batch = new SpriteBatch();

        LevelRender.setLevelScheme("level.tmx");
        LevelRender.setLayerRenderer(batch);
        LevelRender.setLayerMovement("Ground", Interpolation.smooth);

        IParser parser = new ParserLevelFromTxt(LevelRender.getWidth(), LevelRender.getHeight());
        ParseResult parseResult = parser.parse("src/main/resources/positions.txt");

        IDataLevel dataLevel = new LevelDataFromFile(parseResult);
        level = dataLevel.createLevel();
        LevelRender.setObstacle(level.levelObstacle);

        directionController = new DirectionMoveController();
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        clearScreen();

        Direction direction = directionController.getDirection(Gdx.input);

        if (direction != null) {
            calculateDestinationPosition(this.level.playerTank.entity, direction);
        }

        movingTank(level.playerTank, deltaTime);


        for (IGameObject tank : this.level.levelTanks.getGameObjects()) {
            Direction randomDirection = Direction.getRandom();

            if (randomDirection != null) {
                calculateDestinationPosition((TankEntity) tank.getEntity(), randomDirection);
            }

            movingTank(tank, deltaTime);
        }

        LevelRender.render();

        batch.begin();

        drawModel(this.level.playerTank);

        for (IGameObject gameObject : level.levelObstacle.getGameObjects()) {
            drawModel(gameObject);
        }

        for (IGameObject gameObject : level.levelTanks.getGameObjects()) {
            drawModel(gameObject);
        }

        batch.end();
    }

    private void movingTank(IGameObject gameObject, float deltaTime) {
        ModelTexture texture = gameObject.getModelTexture();
        TankEntity tankEntity = (TankEntity) gameObject.getEntity();

        LevelRender.mapMovements.get("Ground")
                .moveRectangleBetweenTileCenters(texture.getRectangle(), tankEntity);
        interpolatedPosition(tankEntity, deltaTime);
    }

    private void calculateDestinationPosition(IMoveEntity moveEntity, Direction direction) {
        if (!isEqual(moveEntity.getMovementProgress(), 1f)) return;

        Transform newTransform = direction.stepInTheDirection(moveEntity.getTransform());
        GridPoint2 position = newTransform.getPosition();

        boolean isObstaclePosition = level.levelObstacle.getPositions().contains(position);
        boolean isEndLevel = (position.x >= level.width) ||
                (position.x < 0) ||
                (position.y < 0) ||
                (position.y >= level.height);

        if (!isObstaclePosition && !isEndLevel) {
            moveEntity.setDestinationTransform(newTransform);
        }
    }

    private void drawModel(IGameObject gameObject) {
        ModelTexture texture = gameObject.getModelTexture();
        Transform transform = gameObject.getEntity().transform;

        drawTextureRegionUnscaled(batch, texture.getTextureRegion(),
                texture.getRectangle(),
                transform.getRotation());
    }

    private void interpolatedPosition(TankEntity entity, float deltaTime) {
        float newProgress = continueProgress(entity.getMovementProgress(), deltaTime, MOVEMENT_SPEED);
        entity.setMovementProgress(newProgress);
        if (isEqual(entity.getMovementProgress(), 1f)) {
            entity.setDestinationPositionAsPosition();
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
        for (IGameObject gameObject : level.levelObstacle.getGameObjects()) {
            gameObject.getModelTexture().getTexture().dispose();
        }
        for (IGameObject gameObject : level.levelObstacle.getGameObjects()) {
            gameObject.getModelTexture().getTexture().dispose();
        }


        this.level.playerTank.texture.getTexture().dispose();
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
