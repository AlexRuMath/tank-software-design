package ru.mipt.bit.platformer.level.dto;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.gameobjects.interfaces.IGameObject;

import java.util.HashSet;

public interface ILevelObstacle {
    void addObstacle(IGameObject gameObject);

    HashSet<GridPoint2> getPositions();

    HashSet<IGameObject> getGameObjects();
}
