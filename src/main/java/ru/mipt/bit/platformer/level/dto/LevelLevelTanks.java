package ru.mipt.bit.platformer.level.dto;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.BaseEntity;
import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.gameobjects.TankGameObject;

import java.util.HashSet;

public class LevelLevelTanks implements ILevelTanks {
    private HashSet<TankGameObject> tanks;

    public LevelLevelTanks(){
        this.tanks = new HashSet<>();
    }

    @Override
    public void addTank(TankGameObject gameObject) {
        this.tanks.add(gameObject);
    }

    @Override
    public void removeTank(TankGameObject gameObject) {
        this.tanks.remove(gameObject);
    }

    @Override
    public void removeByEntity(BaseEntity entity) {
        for(TankGameObject gameObject: tanks){
            if(entity == gameObject.entity){
                tanks.remove(gameObject);
                break;
            }
        }
    }

    @Override
    public HashSet<TankGameObject> getGameObjects() {
        return this.tanks;
    }

    @Override
    public HashSet<GridPoint2> getPositions() {
        HashSet<GridPoint2> positions = new HashSet<>();

        for(TankGameObject gameObject: tanks){
            positions.add(gameObject.entity.transform.position);
        }

        return positions;
    }

    @Override
    public HashSet<TankEntity> getEntities() {
        HashSet<TankEntity> entities = new HashSet<>();

        for (TankGameObject tankGameObject: tanks){
            entities.add(tankGameObject.entity);
        }

        return entities;
    }
}
