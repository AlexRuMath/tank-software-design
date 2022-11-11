package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.entity.interfaces.IMoveEntity;
import ru.mipt.bit.platformer.gameobjects.interfaces.IGameObject;
import ru.mipt.bit.platformer.gameobjects.TankGameObject;
import ru.mipt.bit.platformer.level.dto.ILevelObstacle;
import ru.mipt.bit.platformer.level.dto.ITanks;
import ru.mipt.bit.platformer.level.dto.LevelBullet;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Level {
    public ILevelObstacle levelObstacle;
    public ITanks levelTanks;
    public LevelBullet levelBullets;

    public TankGameObject playerTank;

    public LevelSize levelSize;

    public int height;
    public int width;

    public Level(ILevelObstacle levelObstacle,
                 ITanks levelTanks,
                 LevelBullet levelBullet,
                 TankGameObject playerTank,
                 LevelSize levelSize) {
        this.levelObstacle = levelObstacle;
        this.levelTanks = levelTanks;
        this.playerTank = playerTank;
        this.levelBullets = levelBullet;
        this.levelSize = levelSize;
    }

    public void dispose() {
        for (IGameObject gameObject : levelObstacle.getGameObjects()) {
            gameObject.getModelTexture().texture.dispose();
        }
        for (IGameObject gameObject : levelObstacle.getGameObjects()) {
            gameObject.getModelTexture().texture.dispose();
        }
        for (IGameObject gameObject : levelBullets.getGameObjects()) {
            gameObject.getModelTexture().texture.dispose();
        }

        playerTank.getModelTexture().texture.dispose();
    }

    private void interpolatedPosition(IMoveEntity entity, float deltaTime) {
        float newProgress = continueProgress(entity.getMovementProgress(), deltaTime, entity.getMovementSpeed());
        entity.setMovementProgress(newProgress);
        if (isEqual(entity.getMovementProgress(), 1f)) {
            entity.setDestinationTransformAsCurrentTransform();
        }
    }

    private void movingObject(IGameObject gameObject, float deltaTime, LevelRender levelRender) {
        IMoveEntity moveEntity = (IMoveEntity) gameObject.getEntity();
        interpolatedPosition(moveEntity, deltaTime);

        Rectangle rectangle = gameObject.getModelTexture().rectangle;
        levelRender.moveRectangle("Ground", rectangle, moveEntity);
    }

    public void movingObjectsInLevel(float deltaTime, LevelRender levelRender) {
        movingObject(playerTank, deltaTime, levelRender);

        for (IGameObject tank : levelTanks.getGameObjects()) {
            movingObject(tank, deltaTime, levelRender);
        }

        for (IGameObject bullet : levelBullets.getGameObjects()) {
            movingObject(bullet, deltaTime, levelRender);
        }
    }
}
