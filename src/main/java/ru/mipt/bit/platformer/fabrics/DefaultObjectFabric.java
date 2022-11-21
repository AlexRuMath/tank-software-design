package ru.mipt.bit.platformer.fabrics;

import ru.mipt.bit.platformer.entity.*;
import ru.mipt.bit.platformer.entity.interfaces.IGun;
import ru.mipt.bit.platformer.entity.interfaces.IHealth;
import ru.mipt.bit.platformer.entity.interfaces.IMoveablePart;
import ru.mipt.bit.platformer.entity.parts.MovePart;
import ru.mipt.bit.platformer.entity.parts.TankGun;
import ru.mipt.bit.platformer.entity.parts.HealthEntity;
import ru.mipt.bit.platformer.gameobjects.BulletGameObject;
import ru.mipt.bit.platformer.gameobjects.HealthBarGameObject;
import ru.mipt.bit.platformer.gameobjects.TankGameObject;
import ru.mipt.bit.platformer.gameobjects.TreeGameObject;
import ru.mipt.bit.platformer.gameobjects.interfaces.IDynamicObject;
import ru.mipt.bit.platformer.states.EasyState;
import ru.mipt.bit.platformer.states.IState;
import ru.mipt.bit.platformer.states.States;
import ru.mipt.bit.platformer.util.Transform;

public class DefaultObjectFabric implements IObjectFabric {
    private final int TANK_HEALTH = 10;
    private final float TANK_SPEED_DEFAULT = 0.5f;

    private final float GUN_RELOAD_TIME = 0.4f;

    private final int GUN_DAMAGE = 1;
    private final int BULLET_HEATLH = 1;
    private final float BULLET_SPEED = 0.2f;

    @Override
    public TankGameObject createTank(Transform position, String pathToTexture) {
        ModelTexture tankTexture = new ModelTexture(pathToTexture);

        IState tankState = new EasyState();
        IHealth tankHealth = new HealthEntity(this.TANK_HEALTH);
        IMoveablePart tankMovePart = new MovePart(this.TANK_SPEED_DEFAULT, 0f, position, Transform.valueOf(position));
        IGun tankGun = new TankGun(this.GUN_DAMAGE, this.GUN_RELOAD_TIME);

        TankEntity tankEntity = new TankEntity(tankState, tankHealth, tankMovePart, tankGun);
        TankGameObject gameObject = new TankGameObject(tankEntity, tankTexture);

        return gameObject;
    }

    @Override
    public TreeGameObject createTree(Transform position, String pathToTexture) {
        ModelTexture treeTexture = new ModelTexture(pathToTexture);
        TreeEntity treeEntity = new TreeEntity(position);
        TreeGameObject gameObject = new TreeGameObject(treeEntity, treeTexture);

        return gameObject;
    }

    @Override
    public BulletGameObject createBullet(Transform position, String pathToTexture, IDynamicObject owner, Transform direction) {
        ModelTexture texture = new ModelTexture(pathToTexture);

        IHealth bulletHealth = new HealthEntity(this.BULLET_HEATLH);
        IMoveablePart bulletMoveable = new MovePart(this.BULLET_SPEED, 0f, direction.stepInTheDirection(position), position);

        BulletEntity bulletEntity = new BulletEntity(owner, bulletHealth, bulletMoveable);
        BulletGameObject bulletGameObject = new BulletGameObject(bulletEntity, texture);

        return bulletGameObject;
    }
}
