package ru.mipt.bit.platformer.level;

import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.gameobjects.IGameObject;
import ru.mipt.bit.platformer.gameobjects.TankGameObject;
import ru.mipt.bit.platformer.level.DTO.ILevelObstacle;
import ru.mipt.bit.platformer.level.DTO.ITanks;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Level {
    public ILevelObstacle levelObstacle;
    public ITanks levelTanks;
    public TankGameObject playerTank;

    public int height;
    public int width;

    public Level(ILevelObstacle levelObstacle, ITanks levelTanks, TankGameObject playerTank, int height, int width){
        this.levelObstacle = levelObstacle;
        this.levelTanks = levelTanks;
        this.playerTank = playerTank;
        this.height = height;
        this.width = width;
    }

    public void dispose(){
        for (IGameObject gameObject : levelObstacle.getGameObjects()) {
            gameObject.getModelTexture().getTexture().dispose();
        }
        for (IGameObject gameObject : levelObstacle.getGameObjects()) {
            gameObject.getModelTexture().getTexture().dispose();
        }

        playerTank.getModelTexture().getTexture().dispose();
    }

    private void interpolatedPosition(TankEntity entity, float deltaTime) {
        float newProgress = continueProgress(entity.getMovementProgress(), deltaTime, TankEntity.movementSpeed);
        entity.setMovementProgress(newProgress);
        if (isEqual(entity.getMovementProgress(), 1f)) {
            entity.setDestinationPositionAsPosition();
        }
    }

    private void movingTank(IGameObject gameObject, float deltaTime, LevelRender levelRender){
        TankEntity tankEntity = (TankEntity) gameObject.getEntity();
        interpolatedPosition(tankEntity, deltaTime);

        levelRender.moveRectangle("Ground", gameObject);
    }

    public void movingObject(float deltaTime, LevelRender levelRender){
        movingTank(playerTank, deltaTime, levelRender);

        for (IGameObject tank : levelTanks.getGameObjects()) {
            movingTank(tank, deltaTime, levelRender);
        }
    }
}
