package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.entity.interfaces.IGameEntity;
import ru.mipt.bit.platformer.entity.interfaces.IGun;
import ru.mipt.bit.platformer.entity.interfaces.IMoveEntity;
import ru.mipt.bit.platformer.entity.interfaces.IMoveablePart;
import ru.mipt.bit.platformer.gameobjects.interfaces.IDynamicObject;
import ru.mipt.bit.platformer.gameobjects.interfaces.IGameObject;
import ru.mipt.bit.platformer.gameobjects.TankGameObject;
import ru.mipt.bit.platformer.level.dto.ILevelObstacle;
import ru.mipt.bit.platformer.level.dto.ILevelTanks;
import ru.mipt.bit.platformer.level.dto.LevelBullet;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Level {
    public ILevelObstacle levelObstacle;
    public ILevelTanks levelTanks;
    public LevelBullet levelBullets;

    public TankGameObject playerTank;

    public LevelSize levelSize;

    public Level(ILevelObstacle levelObstacle,
                 ILevelTanks levelTanks,
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
            gameObject.disposeTexture();
        }
        for (IGameObject gameObject : levelObstacle.getGameObjects()) {
            gameObject.disposeTexture();
        }
        for (IGameObject gameObject : levelBullets.getGameObjects()) {
            gameObject.disposeTexture();
        }

        playerTank.disposeTexture();
    }

    private void movingObject(IDynamicObject dynamicObject, float deltaTime, LevelRender levelRender) {
        IMoveablePart movablePart = dynamicObject.getMoveablePart();
        movablePart.continueProgress(deltaTime);

        Rectangle rectangle = dynamicObject.getModelTexture().rectangle;
        levelRender.moveRectangle("Ground", rectangle, movablePart);
    }

    public void movingObjectsInLevel(float deltaTime, LevelRender levelRender) {
        movingObject(playerTank, deltaTime, levelRender);
        playerTank.live(deltaTime);

        for (IDynamicObject tank : levelTanks.getGameObjects()) {
            movingObject(tank, deltaTime, levelRender);
            tank.live(deltaTime);
        }

        for (IDynamicObject bullet : levelBullets.getGameObjects()) {
            movingObject(bullet, deltaTime, levelRender);
        }
    }
}
