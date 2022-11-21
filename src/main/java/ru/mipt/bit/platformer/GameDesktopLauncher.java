package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.collision.Collision;
import ru.mipt.bit.platformer.collision.requests.EndLevelCollisionRequest;
import ru.mipt.bit.platformer.collision.requests.TankCollisionRequest;
import ru.mipt.bit.platformer.collision.requests.TreeCollisionRequest;
import ru.mipt.bit.platformer.commands.ICommand;
import ru.mipt.bit.platformer.commands.ICommandGenerator;
import ru.mipt.bit.platformer.commands.generator.BotBasedCommandGenerator;
import ru.mipt.bit.platformer.commands.generator.BulletMovedCommandGenerator;
import ru.mipt.bit.platformer.commands.generator.InputBasedCommandGenerator;
import ru.mipt.bit.platformer.generators.LevelFromFileData;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.level.LevelRender;
import ru.mipt.bit.platformer.level.dto.ILevelData;
import ru.mipt.bit.platformer.parser.IParser;
import ru.mipt.bit.platformer.parser.ParseResult;
import ru.mipt.bit.platformer.parser.ParserLevelFromTxt;

import java.util.ArrayList;
import java.util.Collection;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GameDesktopLauncher implements ApplicationListener {
    private final LevelRender levelRender = LevelRender.getInstance();

    private Batch batch;

    private Level level;

    private Collision levelCollision;

    private Collection<ICommandGenerator> commandGenerators;

    @Override
    public void create() {
        batch = new SpriteBatch();
        commandGenerators = new ArrayList<>();
        levelCollision = new Collision();

        levelRender.setLevelScheme("level.tmx");
        levelRender.setLayerRenderer(batch);
        levelRender.setLayerMovement("Ground", Interpolation.linear);

        IParser parser = new ParserLevelFromTxt(levelRender.getWidth(), levelRender.getHeight());
        ParseResult parseResult = parser.parse("src/main/resources/positions.txt");

        ILevelData dataLevel = new LevelFromFileData(parseResult);
        level = dataLevel.createLevel();
        levelRender.setObstacle(level.levelObstacle);

        configureObjects();
    }

    private void configureObjects() {
        levelCollision.addRequest(new TankCollisionRequest());
        levelCollision.addRequest(new TreeCollisionRequest());
        levelCollision.addRequest(new EndLevelCollisionRequest());

        commandGenerators.add(new InputBasedCommandGenerator(level.playerTank, level, levelCollision));
        commandGenerators.add(new BotBasedCommandGenerator(level.levelTanks.getGameObjects(), level, levelCollision));
        commandGenerators.add(new BulletMovedCommandGenerator(level, levelCollision));
    }

    @Override
    public void render() {
        clearScreen();
        handleCommands();
        live();
        levelRender.render();
        drawing();
    }

    private void drawing() {
        batch.begin();
        levelRender.renderLevelObject(level.getAllGameObject(), batch);
        batch.end();
    }

    private void handleCommands() {
        for (ICommandGenerator commandGenerator : commandGenerators) {
            for (ICommand command : commandGenerator.generateCommands()) {
                command.execute();
            }
        }
    }

    private void live() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        level.movingObjectsInLevel(deltaTime, levelRender);
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
        level.dispose();
        levelRender.dispose();
        batch.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
