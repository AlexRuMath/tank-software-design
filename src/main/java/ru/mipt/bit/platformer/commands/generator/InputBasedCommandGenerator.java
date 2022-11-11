package ru.mipt.bit.platformer.commands.generator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ru.mipt.bit.platformer.collision.Collision;
import ru.mipt.bit.platformer.commands.ICommand;
import ru.mipt.bit.platformer.commands.ICommandGenerator;
import ru.mipt.bit.platformer.commands.command.MoveCommand;
import ru.mipt.bit.platformer.commands.command.ShootCommand;
import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.entity.interfaces.IMoveEntity;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.util.Direction;
import ru.mipt.bit.platformer.util.Transform;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

import static com.badlogic.gdx.Input.Keys.*;

public class InputBasedCommandGenerator implements ICommandGenerator {
    private final IMoveEntity moveEntity;
    private final Level level;

    private final Collision collision;

    public InputBasedCommandGenerator(IMoveEntity moveEntity, Level level, Collision collision) {
        this.moveEntity = moveEntity;
        this.level = level;
        this.collision = collision;
    }


    @Override
    public Collection<ICommand> generateCommands() {
        Queue<ICommand> commandQueue = new ArrayDeque<>();
        Input input = Gdx.input;

        if (input.isKeyPressed(UP) || input.isKeyPressed(W)) {
            commandQueue.add(new MoveCommand(Direction.Up, this.moveEntity, level, collision));
        }
        if (input.isKeyPressed(LEFT) || input.isKeyPressed(A)) {
            commandQueue.add(new MoveCommand(Direction.Left, this.moveEntity, level, collision));
        }
        if (input.isKeyPressed(DOWN) || input.isKeyPressed(S)) {
            commandQueue.add(new MoveCommand(Direction.Down, this.moveEntity, level, collision));
        }
        if (input.isKeyPressed(RIGHT) || input.isKeyPressed(D)) {
            commandQueue.add(new MoveCommand(Direction.Right, this.moveEntity, level, collision));
        }
        if (input.isKeyPressed(SPACE)) {
            Direction direction = Direction.fromRotation(this.moveEntity.getTransform().rotation);
            Transform position = direction.stepInTheDirection(this.moveEntity.getTransform());
            TankEntity tankEntity = (TankEntity) this.moveEntity;
            commandQueue.add(new ShootCommand(tankEntity, position, direction, level));
        }

        return commandQueue;
    }
}
