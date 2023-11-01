package ru.mipt.bit.platformer.collision.requests;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.collision.CollisionRequest;
import ru.mipt.bit.platformer.collision.CollisionResponse;
import ru.mipt.bit.platformer.collision.CollisionType;
import ru.mipt.bit.platformer.entity.interfaces.IMoveablePart;
import ru.mipt.bit.platformer.gameobjects.TankGameObject;
import ru.mipt.bit.platformer.gameobjects.interfaces.IDynamicObject;
import ru.mipt.bit.platformer.level.Level;

import java.util.HashSet;

public class TankCollisionRequest implements CollisionRequest {
    @Override
    public CollisionResponse check(IDynamicObject object, GridPoint2 endPosition, Level level) {
        HashSet<IDynamicObject> tanksEntities = new HashSet<>(level.levelTanks.getGameObjects());
        tanksEntities.add(level.playerTank);
        for(IDynamicObject enemyTank: tanksEntities){
            GridPoint2 enemyPos = enemyTank.getMoveablePart().getTransform().position;
            if(endPosition.x == enemyPos.x && endPosition.y == enemyPos.y){
                return new CollisionResponse(CollisionType.Enemy, object, enemyTank);
            }

            IMoveablePart enemyMove = enemyTank.getMoveablePart();
            GridPoint2 enemyDestPos = enemyMove.getDestinationTransform().position;
            if(endPosition.x == enemyDestPos.x && endPosition.y == enemyDestPos.y && enemyMove.getProgress() != 0f){
                return new CollisionResponse(CollisionType.Enemy, object, enemyTank);
            }
        }

        return new CollisionResponse(CollisionType.NoCollision, null, null);
    }
}
