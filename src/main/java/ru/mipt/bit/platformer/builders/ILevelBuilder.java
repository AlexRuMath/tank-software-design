package ru.mipt.bit.platformer.builders;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.level.Level;

public interface ILevelBuilder {
    ILevelBuilder addTree(GridPoint2 position, String pathToTexture);

    ILevelBuilder addTank(GridPoint2 position, String pathToTexture);

    ILevelBuilder addPlayer(GridPoint2 position, String pathToTexture);

    ILevelBuilder setHeight(int height);

    ILevelBuilder setWidth(int width);

    Level create();

    void clear();
}
