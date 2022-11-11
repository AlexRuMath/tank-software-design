package ru.mipt.bit.platformer.commands.command;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.collision.Collision;
import ru.mipt.bit.platformer.collision.CollisionResponse;
import ru.mipt.bit.platformer.collision.CollisionType;
import ru.mipt.bit.platformer.commands.ICommand;
import ru.mipt.bit.platformer.entity.interfaces.IMoveEntity;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.util.Direction;
import ru.mipt.bit.platformer.util.Transform;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class MoveCommand implements ICommand {

    private final Direction direction;
    private final IMoveEntity moveEntity;
    private final Level level;
    private final Collision collision;

    public MoveCommand(Direction direction, IMoveEntity moveEntity, Level level, Collision collision) {
        this.direction = direction;
        this.moveEntity = moveEntity;
        this.level = level;
        this.collision = collision;
    }

    @Override
    public void execute() {
        if (!isEqual(moveEntity.getMovementProgress(), 1f)) return;

        Transform destinationPosition = this.direction.stepInTheDirection(this.moveEntity.getTransform());
        CollisionResponse response = collision.sendRequests(this.moveEntity, destinationPosition.position, this.level);
        if (response.type == CollisionType.NoCollision) {
            moveEntity.setDestinationTransform(destinationPosition);
            moveEntity.setMovementProgress(0f);
        }
    }
}
