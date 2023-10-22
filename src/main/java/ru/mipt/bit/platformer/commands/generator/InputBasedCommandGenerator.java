package ru.mipt.bit.platformer.commands.generator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ru.mipt.bit.platformer.collision.Collision;
import ru.mipt.bit.platformer.commands.ICommand;
import ru.mipt.bit.platformer.commands.ICommandGenerator;
import ru.mipt.bit.platformer.commands.command.MoveCommand;
import ru.mipt.bit.platformer.commands.command.ShootCommand;
import ru.mipt.bit.platformer.gameobjects.interfaces.IDynamicObject;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.util.Transform;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

import static com.badlogic.gdx.Input.Keys.*;

public class InputBasedCommandGenerator implements ICommandGenerator {
    private final IDynamicObject moveEntity;
    private final Level level;

    private final Collision collision;

    public InputBasedCommandGenerator(IDynamicObject moveEntity, Level level, Collision collision) {
        this.moveEntity = moveEntity;
        this.level = level;
        this.collision = collision;
    }


    @Override
    public Collection<ICommand> generateCommands() {
        Queue<ICommand> commandQueue = new ArrayDeque<>();
        Input input = Gdx.input;

        if (input.isKeyPressed(UP) || input.isKeyPressed(W)) {
            commandQueue.add(new MoveCommand(Transform.Up, this.moveEntity, level, collision));
        }
        if (input.isKeyPressed(LEFT) || input.isKeyPressed(A)) {
            commandQueue.add(new MoveCommand(Transform.Left, this.moveEntity, level, collision));
        }
        if (input.isKeyPressed(DOWN) || input.isKeyPressed(S)) {
            commandQueue.add(new MoveCommand(Transform.Down, this.moveEntity, level, collision));
        }
        if (input.isKeyPressed(RIGHT) || input.isKeyPressed(D)) {
            commandQueue.add(new MoveCommand(Transform.Right, this.moveEntity, level, collision));
        }
        if (input.isKeyPressed(SPACE)) {
            commandQueue.add(new ShootCommand(moveEntity, level));
        }

        return commandQueue;
    }
}
