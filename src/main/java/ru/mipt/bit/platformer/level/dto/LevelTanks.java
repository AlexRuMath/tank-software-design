package ru.mipt.bit.platformer.level.dto;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.gameobjects.TankGameObject;

import java.util.HashSet;

public class LevelTanks implements ITanks {
    private HashSet<TankGameObject> tanks;
    private HashSet<GridPoint2> positions;

    public LevelTanks(){
        this.tanks = new HashSet<>();
        this.positions = new HashSet<>();
    }

    @Override
    public void addTank(TankGameObject gameObject) {
        this.tanks.add(gameObject);
        this.positions.add(gameObject.entity.transform.position);
    }

    @Override
    public void removeTank(TankGameObject gameObject) {
        this.tanks.remove(gameObject);
        this.positions.remove(gameObject.entity.transform.position);
    }

    @Override
    public void updatePosition(GridPoint2 oldPosition, GridPoint2 newPosition) {
        this.positions.remove(oldPosition);
        this.positions.add(newPosition);
    }

    @Override
    public HashSet<TankGameObject> getGameObjects() {
        return this.tanks;
    }

    @Override
    public HashSet<GridPoint2> getPositions() {
        return this.positions;
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
