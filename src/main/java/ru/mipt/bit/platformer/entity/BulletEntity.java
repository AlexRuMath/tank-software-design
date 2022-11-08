package ru.mipt.bit.platformer.entity;

import ru.mipt.bit.platformer.entity.interfaces.IMoveEntity;
import ru.mipt.bit.platformer.util.Direction;
import ru.mipt.bit.platformer.util.Transform;

public class BulletEntity extends BaseEntity implements IMoveEntity {

    public Direction direction;
    public final int damage;
    private float movementSpeed = 0.25f;
    private float movementProgress;
    private Transform destinationTransform;
    public TankEntity owner;

    public BulletEntity(int damage, TankEntity owner, Transform transform, Direction direction) {
        super(transform);
        this.owner = owner;
        this.damage = damage;
        this.movementProgress = 0f;
        this.direction = direction;
        this.destinationTransform = direction.stepInTheDirection(transform);
    }

    @Override
    public Transform getTransform() {
        return this.transform;
    }

    @Override
    public Transform getDestinationTransform() {
        return destinationTransform;
    }

    @Override
    public float getMovementProgress() {
        return movementProgress;
    }

    @Override
    public void setDestinationTransform(Transform destination) {
        this.destinationTransform = destination;
    }

    public void setMovementProgress(float movementProgress) {
        if(movementProgress > 1f || movementProgress < 0f)
            throw new IllegalArgumentException("The movement progress does not belong to segment [0;1]: "
                    + movementProgress);

        this.movementProgress = movementProgress;
    }

    @Override
    public void setDestinationTransformAsCurrentTransform() {
        this.transform.copyFromTransform(this.destinationTransform);
    }

    @Override
    public float getMovementSpeed() {
        return this.movementSpeed;
    }
}
