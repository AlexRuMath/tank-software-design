package ru.mipt.bit.platformer.commands.generator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ru.mipt.bit.platformer.commands.ICommand;
import ru.mipt.bit.platformer.commands.ICommandGenerator;
import ru.mipt.bit.platformer.commands.command.MoveCommand;
import ru.mipt.bit.platformer.entity.IMoveEntity;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.util.Direction;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

import static com.badlogic.gdx.Input.Keys.*;

public class InputBasedCommandGenerator implements ICommandGenerator {
    private final IMoveEntity moveEntity;
    private final Level level;

    public InputBasedCommandGenerator(IMoveEntity moveEntity, Level level) {
        this.moveEntity = moveEntity;
        this.level = level;
    }


    @Override
    public Collection<ICommand> generateCommands() {
        Queue<ICommand> commandQueue = new ArrayDeque<>();
        Input input = Gdx.input;

        if (input.isKeyPressed(UP) || input.isKeyPressed(W)) {
            commandQueue.add(new MoveCommand(Direction.Up, this.moveEntity, level));
        }
        if (input.isKeyPressed(LEFT) || input.isKeyPressed(A)) {
            commandQueue.add(new MoveCommand(Direction.Left, this.moveEntity, level));
        }
        if (input.isKeyPressed(DOWN) || input.isKeyPressed(S)) {
            commandQueue.add(new MoveCommand(Direction.Down, this.moveEntity, level));
        }
        if (input.isKeyPressed(RIGHT) || input.isKeyPressed(D)) {
            commandQueue.add(new MoveCommand(Direction.Right, this.moveEntity, level));
        }

        return commandQueue;
    }
}
