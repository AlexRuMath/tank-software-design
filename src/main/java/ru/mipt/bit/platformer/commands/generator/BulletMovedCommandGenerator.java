package ru.mipt.bit.platformer.commands.generator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ru.mipt.bit.platformer.collision.Collision;
import ru.mipt.bit.platformer.commands.ICommand;
import ru.mipt.bit.platformer.commands.ICommandGenerator;
import ru.mipt.bit.platformer.commands.command.MoveBulletCommand;
import ru.mipt.bit.platformer.commands.command.MoveCommand;
import ru.mipt.bit.platformer.commands.command.ShootCommand;
import ru.mipt.bit.platformer.entity.interfaces.IMoveEntity;
import ru.mipt.bit.platformer.gameobjects.BulletGameObject;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.util.Direction;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

import static com.badlogic.gdx.Input.Keys.*;

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

        for(BulletGameObject bulletGameObject: this.level.levelBullets.getGameObjects()){
            commandQueue.add(new MoveBulletCommand(bulletGameObject.entity.direction, bulletGameObject.entity, this.level, collision));
        }

        return commandQueue;
    }
}
