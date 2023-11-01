package ru.mipt.bit.platformer.level.dto;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.gameobjects.interfaces.IDynamicObject;
import ru.mipt.bit.platformer.gameobjects.interfaces.IGameObject;

import java.util.HashSet;

public interface ILevelTanks {
    void addTank(IDynamicObject gameObject);
    void removeTank(IGameObject gameObject);

    HashSet<IDynamicObject> getGameObjects();
    HashSet<GridPoint2> getPositions();
    HashSet<TankEntity> getEntities();
}
