package ru.mipt.bit.platformer.entity;

import ru.mipt.bit.platformer.entity.interfaces.IGameEntity;
import ru.mipt.bit.platformer.entity.interfaces.IGun;
import ru.mipt.bit.platformer.entity.interfaces.IHealth;
import ru.mipt.bit.platformer.entity.interfaces.IMoveablePart;
import ru.mipt.bit.platformer.util.Transform;

public class TankEntity implements IGameEntity {
    public final IHealth health;
    public final IMoveablePart movePart;
    public final IGun gunPart;

    public TankEntity(IHealth health, IMoveablePart movePart, IGun gunPart) {
        this.health = health;
        this.movePart = movePart;
        this.gunPart = gunPart;
    }

    @Override
    public Transform getTransform() {
        return this.movePart.getTransform();
    }
}
