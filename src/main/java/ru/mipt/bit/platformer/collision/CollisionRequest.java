package ru.mipt.bit.platformer.collision;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.gameobjects.interfaces.IDynamicObject;
import ru.mipt.bit.platformer.level.Level;

public interface CollisionRequest {
    CollisionResponse check(IDynamicObject object, GridPoint2 endPosition, Level level);
}
