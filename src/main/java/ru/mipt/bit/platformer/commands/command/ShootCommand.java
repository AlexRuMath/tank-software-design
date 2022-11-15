package ru.mipt.bit.platformer.commands.command;

import ru.mipt.bit.platformer.commands.ICommand;
import ru.mipt.bit.platformer.entity.interfaces.IMoveablePart;
import ru.mipt.bit.platformer.fabrics.DefaultObjectFabric;
import ru.mipt.bit.platformer.fabrics.IObjectFabric;
import ru.mipt.bit.platformer.gameobjects.BulletGameObject;
import ru.mipt.bit.platformer.gameobjects.interfaces.IDynamicObject;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.util.Transform;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class ShootCommand implements ICommand {
    private final Level level;
    private final IDynamicObject owner;

    public ShootCommand(IDynamicObject owner, Level level) {
        this.level = level;
        this.owner = owner;
    }

    @Override
    public void execute() {
        if(this.owner.getGun().isReload()) return;

        IMoveablePart moveablePart = this.owner.getMoveablePart();
        IObjectFabric objectFabric = new DefaultObjectFabric();

        Transform direction = Transform.fromRotation(moveablePart.getTransform().rotation);
        Transform position = Transform.valueOf(moveablePart.getTransform());

        BulletGameObject bulletGameObject = objectFabric
                .createBullet(position, "images/bulletBlue3_outline.png", this.owner, direction);

        this.level.levelBullets.addBullet(bulletGameObject);
        this.owner.getGun().reload();
    }
}
