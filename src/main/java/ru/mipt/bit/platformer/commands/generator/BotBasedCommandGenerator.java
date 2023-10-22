package ru.mipt.bit.platformer.commands.generator;

import ru.mipt.bit.platformer.collision.Collision;
import ru.mipt.bit.platformer.commands.ICommand;
import ru.mipt.bit.platformer.commands.ICommandGenerator;
import ru.mipt.bit.platformer.commands.command.MoveCommand;
import ru.mipt.bit.platformer.entity.interfaces.IMoveEntity;
import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.gameobjects.TankGameObject;
import ru.mipt.bit.platformer.gameobjects.interfaces.IDynamicObject;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.util.Transform;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashSet;
import java.util.Queue;

public class BotBasedCommandGenerator implements ICommandGenerator {
    private final HashSet<IDynamicObject> tankEntities;
    private final Level level;
    private final Collision collision;

    public BotBasedCommandGenerator(HashSet<IDynamicObject> tankGameObjects, Level level, Collision collision) {
        this.tankEntities = tankGameObjects;
        this.level = level;
        this.collision = collision;
    }


    @Override
    public Collection<ICommand> generateCommands() {
        Queue<ICommand> commands = new ArrayDeque<>();

        for(IDynamicObject entity: tankEntities){
            MoveCommand command = new MoveCommand(Transform.getRandom(), entity, level, collision);
            commands.add(command);
        }

        return commands;
    }
}
