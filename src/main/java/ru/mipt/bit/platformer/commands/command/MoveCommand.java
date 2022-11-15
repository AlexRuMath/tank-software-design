package ru.mipt.bit.platformer.commands.command;

import ru.mipt.bit.platformer.collision.Collision;
import ru.mipt.bit.platformer.collision.CollisionResponse;
import ru.mipt.bit.platformer.collision.CollisionType;
import ru.mipt.bit.platformer.commands.ICommand;
import ru.mipt.bit.platformer.entity.interfaces.IMoveablePart;
import ru.mipt.bit.platformer.gameobjects.interfaces.IDynamicObject;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.util.Transform;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class MoveCommand implements ICommand {

    private final Transform direction;
    private final IDynamicObject dynamicObject;
    private final Level level;
    private final Collision collision;

    public MoveCommand(Transform direction, IDynamicObject object, Level level, Collision collision) {
        this.direction = direction;
        this.dynamicObject = object;
        this.level = level;
        this.collision = collision;
    }

    @Override
    public void execute() {
        IMoveablePart moveablePart = this.dynamicObject.getMoveablePart();

        if (!isEqual(moveablePart.getProgress(), 1f)) return;

        Transform objectTransform = moveablePart.getTransform();
        Transform destinationPosition = this.direction.stepInTheDirection(objectTransform);
        CollisionResponse response = collision.sendRequests(this.dynamicObject, destinationPosition.position, this.level);
        if (response.type == CollisionType.NoCollision) {
            moveablePart.setDestinationTransform(destinationPosition);
        }
    }
}
