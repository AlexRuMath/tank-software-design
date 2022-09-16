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
import ru.mipt.bit.platformer.controllers.IMove;
import ru.mipt.bit.platformer.controllers.PlayerMoveController;
import ru.mipt.bit.platformer.entity.BaseEntity;
import ru.mipt.bit.platformer.entity.PlayerEntity;
import ru.mipt.bit.platformer.level.ILevelObstacle;
import ru.mipt.bit.platformer.level.LevelRender;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class GameDesktopLauncher implements ApplicationListener {

    private static final float MOVEMENT_SPEED = 0.4f;

    private final LevelRender LevelRender = ru.mipt.bit.platformer.level.LevelRender.getInstance();
    private ILevelObstacle levelObstacle;

    private Batch batch;

    private PlayerEntity playerEntity;

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

        IMove playerMoveController = new PlayerMoveController();
        playerEntity = new PlayerEntity("images/tank_blue.png", new GridPoint2(1, 1), 0f, playerMoveController);
    }

    @Override
    public void render() {
        // clear the screen
        clearScreen();

        // get time passed since the last render
        playerEntity.getInput(Gdx.input, levelObstacle);

        // calculate interpolated player screen coordinates
        movePlayer();

        // render each tile of the level
        LevelRender.render();

        // start recording all drawing commands
        batch.begin();

        // render player
        playerEntity.draw(batch);

        // render obstacle
        for (BaseEntity entity: levelObstacle.getEntities()) entity.draw(batch);

        // submit all drawing requests
        batch.end();
    }

    private void movePlayer() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        LevelRender.mapMovements.get("Ground").moveRectangleBetweenTileCenters(playerEntity.rectangle, playerEntity.position, playerEntity.destinitionPosition, playerEntity.movementProgress);

        playerEntity.movementProgress = continueProgress(playerEntity.movementProgress, deltaTime, MOVEMENT_SPEED);
        if (isEqual(playerEntity.movementProgress, 1f)) {
            // record that the player has reached his/her destination
            playerEntity.position.set(playerEntity.destinitionPosition);
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
        for (BaseEntity entity: levelObstacle.getEntities()) {
            entity.texture.dispose();
        }

        playerEntity.texture.dispose();
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
