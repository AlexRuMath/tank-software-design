package ru.mipt.bit.platformer.commands.command;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.collision.Collision;
import ru.mipt.bit.platformer.collision.CollisionResponse;
import ru.mipt.bit.platformer.collision.CollisionType;
import ru.mipt.bit.platformer.commands.ICommand;
import ru.mipt.bit.platformer.entity.BulletEntity;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.util.Direction;
import ru.mipt.bit.platformer.util.Transform;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class MoveBulletCommand implements ICommand {

    private final Direction direction;
    private final BulletEntity bulletEntity;
    private final Level level;
    private final Collision collision;

    public MoveBulletCommand(Direction direction, BulletEntity bulletEntity, Level level, Collision collision) {
        this.direction = direction;
        this.bulletEntity = bulletEntity;
        this.level = level;
        this.collision = collision;
    }

    @Override
    public void execute() {
        if (!isEqual(bulletEntity.getMovementProgress(), 1f)) return;

        Transform destinationPosition = this.direction.stepInTheDirection(this.bulletEntity.getTransform());
        CollisionResponse response = collision.sendRequests(this.bulletEntity, destinationPosition.position, this.level);

        if (response.type == CollisionType.NoCollision) {
            bulletEntity.setDestinationTransform(destinationPosition);
            bulletEntity.setMovementProgress(0f);
            return;
        }

        if(response.type == CollisionType.Enemy){
            level.levelBullets.removeByEntity(bulletEntity);
            level.levelTanks.removeByEntity(response.secondObject);
        }

        bulletEntity.owner.isShoot = false;
        level.levelBullets.removeByEntity(bulletEntity);
    }
}
