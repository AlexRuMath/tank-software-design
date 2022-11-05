package ru.mipt.bit.platformer.commands.generator;

import org.awesome.ai.Recommendation;
import ru.mipt.bit.platformer.commands.ICommand;
import ru.mipt.bit.platformer.commands.ICommandGenerator;
import ru.mipt.bit.platformer.commands.command.MoveCommand;
import ru.mipt.bit.platformer.converters.ConverterMoveCommand;
import ru.mipt.bit.platformer.level.Level;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

public class BotAICommandGenerator implements ICommandGenerator {
    private final List<Recommendation> recommendations;
    private final Level level;

    public BotAICommandGenerator(List<Recommendation> recommendations, Level level) {
        this.recommendations = recommendations;
        this.level = level;
    }


    @Override
    public Collection<ICommand> generateCommands() {
        Queue<ICommand> commands = new ArrayDeque<>();

        for (Recommendation recommendation: recommendations){
            MoveCommand command = ConverterMoveCommand.convert(recommendation, level);
            commands.add(command);
        }

        return commands;
    }
}
