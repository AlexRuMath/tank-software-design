package ru.mipt.bit.platformer.entity;

import ru.mipt.bit.platformer.entity.interfaces.IGameEntity;
import ru.mipt.bit.platformer.entity.interfaces.IHealth;
import ru.mipt.bit.platformer.entity.interfaces.IMoveablePart;
import ru.mipt.bit.platformer.gameobjects.interfaces.IDynamicObject;
import ru.mipt.bit.platformer.util.Transform;

public class BulletEntity implements IGameEntity {

    public final IDynamicObject owner;

    public final IMoveablePart moveablePart;

    public final IHealth health;


    public BulletEntity(IDynamicObject owner, IHealth health, IMoveablePart moveablePart) {
        this.owner = owner;
        this.health = health;
        this.moveablePart = moveablePart;
    }

    @Override
    public Transform getTransform() {
        return this.moveablePart.getTransform();
    }
}
