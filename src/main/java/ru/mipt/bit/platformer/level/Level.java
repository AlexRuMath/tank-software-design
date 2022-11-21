package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.entity.TreeEntity;
import ru.mipt.bit.platformer.entity.interfaces.IMoveablePart;
import ru.mipt.bit.platformer.gameobjects.interfaces.IDynamicObject;
import ru.mipt.bit.platformer.gameobjects.interfaces.IGameObject;
import ru.mipt.bit.platformer.gameobjects.TankGameObject;
import ru.mipt.bit.platformer.level.dto.ILevelObstacle;
import ru.mipt.bit.platformer.level.dto.ILevelTanks;
import ru.mipt.bit.platformer.level.dto.LevelBullet;

import java.util.HashSet;

import static com.badlogic.gdx.math.MathUtils.isEqual;

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
        HashSet<IGameObject> allObjects = this.getAllGameObject();
        for(IGameObject gameObject : allObjects){
            gameObject.disposeTexture();
        }
    }

    private void movingObject(IDynamicObject dynamicObject, float deltaTime, LevelRender levelRender) {
        IMoveablePart movablePart = dynamicObject.getMoveablePart();
        movablePart.continueProgress(deltaTime);

        Rectangle rectangle = dynamicObject.getModelTexture().rectangle;
        levelRender.moveRectangle("Ground", rectangle, movablePart);
    }

    public void movingObjectsInLevel(float deltaTime, LevelRender levelRender) {
        HashSet<IGameObject> allObjects = this.getAllGameObject();
        for(IGameObject gameObject : allObjects){
            if(gameObject.getGameEntity().getClass() == TreeEntity.class) continue;

            IDynamicObject dynamicObject = ((IDynamicObject) gameObject);
            movingObject(dynamicObject, deltaTime, levelRender);
            dynamicObject.live(deltaTime);
        }
    }

    public HashSet<IGameObject> getAllGameObject(){
        HashSet<IGameObject> result = new HashSet<>();

        result.addAll(levelObstacle.getGameObjects());
        result.addAll(levelBullets.getGameObjects());
        result.addAll(levelTanks.getGameObjects());
        result.add(playerTank);

        return result;
    }
}
