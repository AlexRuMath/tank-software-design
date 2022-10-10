package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.BaseEntity;

import java.util.HashSet;

public class LevelObstacle implements ILevelObstacle {
    private final HashSet<BaseEntity> obstacleSet;
    private final HashSet<GridPoint2> obstaclePositionSet;

    public LevelObstacle(){
        this.obstacleSet = new HashSet<>();
        this.obstaclePositionSet = new HashSet<>();
    }

    public void addObstacle(BaseEntity entity) {
        obstacleSet.add(entity);
        obstaclePositionSet.add(entity.transform.getPosition());
    }

    public void removeObstacle(BaseEntity entity) {
        obstacleSet.remove(entity);
        obstaclePositionSet.remove(entity.transform.getPosition());
    }

    public HashSet<GridPoint2> getPositions(){
        return this.obstaclePositionSet;
    }

    public HashSet<BaseEntity> getEntities(){
        return this.obstacleSet;
    }
}
