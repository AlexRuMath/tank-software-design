package ru.mipt.bit.platformer.collision.requests;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.collision.CollisionRequest;
import ru.mipt.bit.platformer.collision.CollisionResponse;
import ru.mipt.bit.platformer.collision.CollisionType;
import ru.mipt.bit.platformer.entity.interfaces.IMoveEntity;
import ru.mipt.bit.platformer.level.Level;

import java.util.HashSet;

public class TreeCollisionRequest implements CollisionRequest {
    @Override
    public CollisionResponse check(IMoveEntity entity, GridPoint2 endPosition, Level level) {
        HashSet<GridPoint2> treePositions = level.levelObstacle.getPositions();
        boolean isObstaclePosition = treePositions.contains(endPosition);
        if(isObstaclePosition){
            return new CollisionResponse(CollisionType.Obstacle, entity, null);
        }


        return new CollisionResponse(CollisionType.NoCollision, null, null);
    }
}
