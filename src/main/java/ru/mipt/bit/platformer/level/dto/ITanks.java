package ru.mipt.bit.platformer.level.dto;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.BaseEntity;
import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.gameobjects.TankGameObject;
import ru.mipt.bit.platformer.util.Transform;

import java.util.HashSet;

public interface ITanks {
    void addTank(TankGameObject gameObject);
    void removeTank(TankGameObject gameObject);
    void removeByEntity(BaseEntity entity);

    HashSet<TankGameObject> getGameObjects();
    HashSet<GridPoint2> getPositions();
    HashSet<TankEntity> getEntities();
}
