package ru.mipt.bit.platformer.builders;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.ModelTexture;
import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.gameobjects.TankGameObject;
import ru.mipt.bit.platformer.gameobjects.TreeGameObject;
import ru.mipt.bit.platformer.level.*;
import ru.mipt.bit.platformer.level.dto.*;
import ru.mipt.bit.platformer.util.Transform;
import ru.mipt.bit.platformer.entity.TreeEntity;

public class LevelBuilder implements ILevelBuilder {

    private ILevelObstacle levelObstacle;

    private ITanks levelTanks;

    private LevelBullet levelBullet;

    private int height, width;

    private TankGameObject player;

    public LevelBuilder() {
        this.clear();
    }

    @Override
    public ILevelBuilder addTree(GridPoint2 position, String pathToTexture) {
        ModelTexture treeTexture = new ModelTexture(pathToTexture);
        Transform treeTransform = new Transform(position);
        TreeEntity treeEntity = new TreeEntity(treeTransform);
        TreeGameObject gameObject = new TreeGameObject(treeEntity, treeTexture);

        this.levelObstacle.addObstacle(gameObject);
        return this;
    }

    @Override
    public ILevelBuilder addTank(GridPoint2 position, String pathToTexture) {
        ModelTexture tankTexture = new ModelTexture(pathToTexture);
        Transform tankTransform = new Transform(position);
        TankEntity tankEntity = new TankEntity(tankTransform);
        TankGameObject gameObject = new TankGameObject(tankEntity, tankTexture);

        this.levelTanks.addTank(gameObject);
        return this;
    }

    @Override
    public ILevelBuilder addPlayer(GridPoint2 position, String pathToTexture) {
        ModelTexture playerTexture = new ModelTexture(pathToTexture);
        Transform playerTransform = new Transform(position);
        TankEntity playerEntity = new TankEntity(playerTransform);
        TankGameObject gameObject = new TankGameObject(playerEntity, playerTexture);

        this.player = gameObject;
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
        this.levelTanks = new LevelTanks();
        this.levelObstacle = new LevelObstacle();
        this.levelBullet = new LevelBullet();
    }
}
