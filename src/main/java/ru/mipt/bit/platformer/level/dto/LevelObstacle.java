package ru.mipt.bit.platformer.level.dto;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.BaseEntity;
import ru.mipt.bit.platformer.gameobjects.interfaces.IGameObject;

import java.util.HashSet;

public class LevelObstacle implements ILevelObstacle {
    private final HashSet<BaseEntity> obstacleSet;
    private final HashSet<GridPoint2> obstaclePositionSet;
    private final HashSet<IGameObject> obstacleGameObject;

    public LevelObstacle() {
        this.obstacleSet = new HashSet<>();
        this.obstaclePositionSet = new HashSet<>();
        this.obstacleGameObject = new HashSet<>();
    }

    public void addObstacle(IGameObject gameObject) {
        obstacleSet.add(gameObject.getEntity());
        gameObject.getEntity();
        obstaclePositionSet.add(gameObject.getEntity().transform.position);
        obstacleGameObject.add(gameObject);
    }

    public HashSet<GridPoint2> getPositions() {
        return this.obstaclePositionSet;
    }

    public HashSet<IGameObject> getGameObjects() {
        return this.obstacleGameObject;
    }
}
