package ru.mipt.bit.platformer.collision.requests;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.collision.CollisionRequest;
import ru.mipt.bit.platformer.collision.CollisionResponse;
import ru.mipt.bit.platformer.collision.CollisionType;
import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.entity.interfaces.IMoveEntity;
import ru.mipt.bit.platformer.level.Level;

import java.util.HashSet;

public class TankCollisionRequest implements CollisionRequest {
    @Override
    public CollisionResponse check(IMoveEntity entity, GridPoint2 endPosition, Level level) {
        HashSet<TankEntity> tanksEntities = level.levelTanks.getEntities();
        for(TankEntity enemyTank: tanksEntities){
            GridPoint2 enemyPos = enemyTank.transform.position;

            if(endPosition.x == enemyPos.x && endPosition.y == enemyPos.y){
                return new CollisionResponse(CollisionType.Enemy, entity, enemyTank);
            }

            GridPoint2 enemyDestPos = enemyTank.getDestinationTransform().position;
            if(endPosition.x == enemyDestPos.x && endPosition.y == enemyDestPos.y && enemyTank.getMovementProgress() != 0f){
                return new CollisionResponse(CollisionType.Enemy, entity, enemyTank);
            }
        }

        return new CollisionResponse(CollisionType.NoCollision, null, null);
    }
}
