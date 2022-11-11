package ru.mipt.bit.platformer.collision;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.interfaces.IMoveEntity;
import ru.mipt.bit.platformer.level.Level;

public interface CollisionRequest {
    CollisionResponse check(IMoveEntity entity, GridPoint2 endPosition, Level level);
}
