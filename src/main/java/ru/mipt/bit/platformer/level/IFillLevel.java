package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.math.GridPoint2;

public interface IFillLevel {
    ILevelObstacle fill();
    GridPoint2 getPlayerPosition();
}
