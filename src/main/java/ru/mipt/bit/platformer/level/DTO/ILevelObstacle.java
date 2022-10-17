package ru.mipt.bit.platformer.level.DTO;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.BaseEntity;
import ru.mipt.bit.platformer.gameobjects.IGameObject;

import java.util.HashSet;

public interface ILevelObstacle {
    void addObstacle(IGameObject gameObject);

    HashSet<GridPoint2> getPositions();
    HashSet<BaseEntity> getEntities();
    HashSet<IGameObject> getGameObjects();
}
