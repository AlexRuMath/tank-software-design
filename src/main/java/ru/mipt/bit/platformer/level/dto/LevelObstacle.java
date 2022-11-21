package ru.mipt.bit.platformer.level.dto;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.interfaces.IGameEntity;
import ru.mipt.bit.platformer.gameobjects.interfaces.IGameObject;

import java.util.HashSet;

public class LevelObstacle implements ILevelObstacle {
    private final HashSet<IGameEntity> obstacleSet;
    private final HashSet<GridPoint2> obstaclePositionSet;
    private final HashSet<IGameObject> obstacleGameObject;

    public LevelObstacle() {
        this.obstacleSet = new HashSet<>();
        this.obstaclePositionSet = new HashSet<>();
        this.obstacleGameObject = new HashSet<>();
    }

    public void addObstacle(IGameObject gameObject) {
        obstacleSet.add(gameObject.getGameEntity());
        gameObject.getGameEntity();
        obstaclePositionSet.add(gameObject.getGameEntity().getTransform().position);
        obstacleGameObject.add(gameObject);
    }

    public HashSet<GridPoint2> getPositions() {
        return this.obstaclePositionSet;
    }

    public HashSet<IGameObject> getGameObjects() {
        return this.obstacleGameObject;
    }
}
