package ru.mipt.bit.platformer.commands.command;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.commands.ICommand;
import ru.mipt.bit.platformer.entity.IMoveEntity;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.util.Direction;
import ru.mipt.bit.platformer.util.Transform;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class MoveCommand implements ICommand {

    private final Direction direction;
    private final IMoveEntity moveEntity;
    private final Level level;

    public MoveCommand(Direction direction, IMoveEntity moveEntity, Level level) {
        this.direction = direction;
        this.moveEntity = moveEntity;
        this.level = level;
    }

    @Override
    public void execute() {
        if (!isEqual(moveEntity.getMovementProgress(), 1f)) return;

        Transform destinationPosition = this.direction.stepInTheDirection(this.moveEntity.getTransform());
        GridPoint2 position = destinationPosition.getPosition();

        boolean isObstaclePosition = this.level.levelObstacle.getPositions().contains(position);
        boolean isEndLevel = (position.x >= level.width) ||
                (position.x < 0) ||
                (position.y < 0) ||
                (position.y >= level.height);

        if (!isObstaclePosition && !isEndLevel) {
            moveEntity.setDestinationTransform(destinationPosition);
        }
    }
}
