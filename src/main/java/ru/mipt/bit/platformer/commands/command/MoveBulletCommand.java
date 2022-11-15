package ru.mipt.bit.platformer.commands.command;

import ru.mipt.bit.platformer.collision.Collision;
import ru.mipt.bit.platformer.collision.CollisionResponse;
import ru.mipt.bit.platformer.collision.CollisionType;
import ru.mipt.bit.platformer.commands.ICommand;
import ru.mipt.bit.platformer.entity.BulletEntity;
import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.entity.interfaces.IMoveablePart;
import ru.mipt.bit.platformer.gameobjects.TankGameObject;
import ru.mipt.bit.platformer.gameobjects.interfaces.IDynamicObject;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.util.Transform;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class MoveBulletCommand implements ICommand {

    private final Transform direction;
    private final IDynamicObject bulletEntity;
    private final Level level;
    private final Collision collision;

    public MoveBulletCommand(Transform direction, IDynamicObject bulletEntity, Level level, Collision collision) {
        this.direction = direction;
        this.bulletEntity = bulletEntity;
        this.level = level;
        this.collision = collision;
    }

    @Override
    public void execute() {
        IMoveablePart moveablePart = bulletEntity.getMoveablePart();

        if (!isEqual(moveablePart.getProgress(), 1f)) return;

        Transform destinationPosition = this.direction.stepInTheDirection(moveablePart.getTransform());
        CollisionResponse response = collision.sendRequests(this.bulletEntity, destinationPosition.position, this.level);

        if (response.type == CollisionType.NoCollision) {
            moveablePart.setDestinationTransform(destinationPosition);
            return;
        }

        if(response.type == CollisionType.Enemy){
            TankGameObject enemyTank = (TankGameObject) response.collisionObject;
            int damage = -bulletEntity.getGun().getDamage();
            enemyTank.getHealth().updateHealth(damage);

            if(!enemyTank.getHealth().isLive()) level.levelTanks.removeTank(enemyTank);
        }

        level.levelBullets.removeBullet(bulletEntity);
    }
}
