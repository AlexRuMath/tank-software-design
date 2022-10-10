package ru.mipt.bit.platformer.builders;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.level.ILevelObstacle;

public interface ILevelObstacleBuilder {
    ILevelObstacleBuilder addTree(GridPoint2 position);

    ILevelObstacle create();

    void clear();
}
