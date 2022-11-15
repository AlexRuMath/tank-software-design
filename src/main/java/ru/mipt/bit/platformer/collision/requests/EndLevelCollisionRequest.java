package ru.mipt.bit.platformer.collision.requests;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.collision.CollisionRequest;
import ru.mipt.bit.platformer.collision.CollisionResponse;
import ru.mipt.bit.platformer.collision.CollisionType;
import ru.mipt.bit.platformer.entity.interfaces.IMoveEntity;
import ru.mipt.bit.platformer.entity.interfaces.IMoveablePart;
import ru.mipt.bit.platformer.gameobjects.interfaces.IDynamicObject;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.level.LevelSize;
import ru.mipt.bit.platformer.util.Transform;

public class EndLevelCollisionRequest implements CollisionRequest {
    @Override
    public CollisionResponse check(IDynamicObject object, GridPoint2 endPosition, Level level) {
        LevelSize levelSize = level.levelSize;

        if(endPosition.x >= levelSize.maxX || endPosition.x < levelSize.minX ||
                endPosition.y >= levelSize.maxY || endPosition.y < levelSize.minY)
        {
            return new CollisionResponse(CollisionType.EndLevel, object, null);
        }

        return new CollisionResponse(CollisionType.NoCollision, null, null);
    }
}
