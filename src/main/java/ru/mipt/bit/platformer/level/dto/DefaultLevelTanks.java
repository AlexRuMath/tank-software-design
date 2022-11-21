package ru.mipt.bit.platformer.level.dto;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.gameobjects.interfaces.IDynamicObject;
import ru.mipt.bit.platformer.gameobjects.interfaces.IGameObject;

import java.util.HashSet;

public class DefaultLevelTanks implements ILevelTanks {
    private HashSet<IDynamicObject> tanks;

    public DefaultLevelTanks(){
        this.tanks = new HashSet<>();
    }

    @Override
    public void addTank(IDynamicObject gameObject) {
        this.tanks.add(gameObject);
    }

    @Override
    public void removeTank(IGameObject gameObject) {
        this.tanks.remove(gameObject);
    }

    @Override
    public HashSet<IDynamicObject> getGameObjects() {
        return this.tanks;
    }

    @Override
    public HashSet<GridPoint2> getPositions() {
        HashSet<GridPoint2> positions = new HashSet<>();

        for(IDynamicObject gameObject: tanks){
            positions.add(gameObject.getMoveablePart().getTransform().position);
        }

        return positions;
    }

    @Override
    public HashSet<TankEntity> getEntities() {
        HashSet<TankEntity> entities = new HashSet<>();

        for (IDynamicObject tankGameObject: tanks){
            entities.add((TankEntity) tankGameObject.getGameEntity());
        }

        return entities;
    }
}
