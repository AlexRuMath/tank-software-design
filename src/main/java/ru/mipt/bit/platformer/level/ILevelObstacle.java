package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.BaseEntity;

import java.util.HashSet;

public interface ILevelObstacle {
    void addObstacle(BaseEntity obstacle);
    void removeObstacle(BaseEntity obstacle);

    HashSet<GridPoint2> getPositions();
    HashSet<BaseEntity> getEntities();
}
