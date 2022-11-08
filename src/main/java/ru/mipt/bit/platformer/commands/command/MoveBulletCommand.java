package ru.mipt.bit.platformer.commands.command;

import com.badlogic.gdx.math.GridPoint2;
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

    public MoveBulletCommand(Direction direction, BulletEntity bulletEntity, Level level) {
        this.direction = direction;
        this.bulletEntity = bulletEntity;
        this.level = level;
    }

    @Override
    public void execute() {
        if (!isEqual(bulletEntity.getMovementProgress(), 1f)) return;

        Transform destinationPosition = this.direction.stepInTheDirection(this.bulletEntity.transform);
        GridPoint2 position = destinationPosition.position;

        boolean isObstaclePosition = this.level.levelObstacle.getPositions().contains(position);
        boolean isTank = this.level.levelTanks.getPositions().contains(position);
        boolean isEndLevel =    (position.x >= level.width) ||
                                (position.x < 0) ||
                                (position.y < 0) ||
                                (position.y >= level.height);

        if(isObstaclePosition || isTank || isEndLevel) {
            this.bulletEntity.owner.isShoot = false;
            this.level.levelBullets.removeByEntity(this.bulletEntity);
            return;
        }

        bulletEntity.setDestinationTransform(destinationPosition);
        bulletEntity.setMovementProgress(0f);
    }
}
