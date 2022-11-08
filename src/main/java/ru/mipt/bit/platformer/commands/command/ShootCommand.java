package ru.mipt.bit.platformer.commands.command;

import ru.mipt.bit.platformer.commands.ICommand;
import ru.mipt.bit.platformer.entity.BulletEntity;
import ru.mipt.bit.platformer.entity.ModelTexture;
import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.entity.interfaces.IMoveEntity;
import ru.mipt.bit.platformer.gameobjects.BulletGameObject;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.util.Direction;
import ru.mipt.bit.platformer.util.Transform;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class ShootCommand implements ICommand {

    private final Direction direction;
    private final Transform start;
    private final Level level;

    private final int DAMAGE = 1;
    private final TankEntity owner;

    public ShootCommand(TankEntity owner, Transform start, Direction direction, Level level) {
        this.direction = direction;
        this.level = level;
        this.start = start;
        this.owner = owner;
    }

    @Override
    public void execute() {
        if(this.owner.isShoot) return;

        BulletEntity bulletEntity = new BulletEntity(DAMAGE, owner, this.start, this.direction);
        ModelTexture texture = new ModelTexture("images/bulletBlue3_outline.png");
        BulletGameObject bulletGameObject = new BulletGameObject(bulletEntity, texture);

        this.level.levelBullets.addBullet(bulletGameObject);
        this.owner.isShoot = true;
    }
}
