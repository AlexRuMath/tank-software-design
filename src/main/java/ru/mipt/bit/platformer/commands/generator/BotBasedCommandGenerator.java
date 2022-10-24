package ru.mipt.bit.platformer.commands.generator;

import ru.mipt.bit.platformer.commands.ICommand;
import ru.mipt.bit.platformer.commands.ICommandGenerator;
import ru.mipt.bit.platformer.commands.command.MoveCommand;
import ru.mipt.bit.platformer.entity.IMoveEntity;
import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.util.Direction;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashSet;
import java.util.Queue;

public class BotBasedCommandGenerator implements ICommandGenerator {
    private final HashSet<TankEntity> tankEntities;
    private final Level level;

    public BotBasedCommandGenerator(HashSet<TankEntity> tankEntities, Level level) {
        this.tankEntities = tankEntities;
        this.level = level;
    }


    @Override
    public Collection<ICommand> generateCommands() {
        Queue<ICommand> commands = new ArrayDeque<>();

        for(IMoveEntity entity: tankEntities){
            MoveCommand command = new MoveCommand(Direction.getRandom(), entity, level);
            commands.add(command);
        }

        return commands;
    }
}
