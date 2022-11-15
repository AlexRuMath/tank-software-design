package ru.mipt.bit.platformer.commands.generator;

import ru.mipt.bit.platformer.collision.Collision;
import ru.mipt.bit.platformer.commands.ICommand;
import ru.mipt.bit.platformer.commands.ICommandGenerator;
import ru.mipt.bit.platformer.commands.command.MoveBulletCommand;
import ru.mipt.bit.platformer.gameobjects.BulletGameObject;
import ru.mipt.bit.platformer.gameobjects.interfaces.IDynamicObject;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.util.Transform;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

public class BulletMovedCommandGenerator implements ICommandGenerator {
    private final Level level;
    private final Collision collision;

    public BulletMovedCommandGenerator(Level level, Collision collision) {
        this.collision = collision;
        this.level = level;
    }

    @Override
    public Collection<ICommand> generateCommands() {
        Queue<ICommand> commandQueue = new ArrayDeque<>();

        for(IDynamicObject bulletGameObject: this.level.levelBullets.getGameObjects()){
            Transform direction = Transform.fromRotation(bulletGameObject.getMoveablePart().getTransform().rotation);
            commandQueue.add(new MoveBulletCommand(direction, bulletGameObject, this.level, collision));
        }

        return commandQueue;
    }
}
