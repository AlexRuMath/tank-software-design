package ru.mipt.bit.platformer.builders;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.fabrics.DefaultObjectFabric;
import ru.mipt.bit.platformer.gameobjects.TankGameObject;
import ru.mipt.bit.platformer.gameobjects.TreeGameObject;
import ru.mipt.bit.platformer.level.*;
import ru.mipt.bit.platformer.level.dto.*;
import ru.mipt.bit.platformer.util.Transform;

public class LevelBuilder implements ILevelBuilder {

    private ILevelObstacle levelObstacle;

    private ILevelTanks levelTanks;

    private LevelBullet levelBullet;

    private int height, width;

    private TankGameObject player;

    private DefaultObjectFabric entityFabric;

    public LevelBuilder() {
        this.entityFabric = new DefaultObjectFabric();
        this.clear();
    }

    @Override
    public ILevelBuilder addTree(GridPoint2 position, String pathToTexture) {
        Transform transform = new Transform(position);
        TreeGameObject gameObject = this.entityFabric.createTree(transform, pathToTexture);
        this.levelObstacle.addObstacle(gameObject);
        return this;
    }

    @Override
    public ILevelBuilder addTank(GridPoint2 position, String pathToTexture) {
        Transform transform = new Transform(position);
        TankGameObject gameObject = this.entityFabric.createTank(transform, pathToTexture);
        this.levelTanks.addTank(gameObject);
        return this;
    }

    @Override
    public ILevelBuilder addPlayer(GridPoint2 position, String pathToTexture) {
        Transform transform = new Transform(position);
        this.player = this.entityFabric.createTank(transform, pathToTexture);
        return this;
    }

    @Override
    public ILevelBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    @Override
    public ILevelBuilder setWidth(int width) {
        this.width = width;
        return this;
    }


    @Override
    public Level create() {
        LevelSize levelSize = new LevelSize(width, height, 0, 0);
        Level level = new Level(levelObstacle, levelTanks, levelBullet, player, levelSize);

        this.clear();
        return level;
    }

    @Override
    public void clear() {
        this.levelTanks = new DefaultLevelTanks();
        this.levelObstacle = new LevelObstacle();
        this.levelBullet = new LevelBullet();
    }
}
