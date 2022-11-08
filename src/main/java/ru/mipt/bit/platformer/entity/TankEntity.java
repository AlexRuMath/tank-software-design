package ru.mipt.bit.platformer.entity;

import ru.mipt.bit.platformer.entity.interfaces.IMoveEntity;
import ru.mipt.bit.platformer.util.Transform;

public class TankEntity extends BaseEntity implements IMoveEntity {
    private Transform destinationTransform;
    private final float movementSpeed = 0.4f;
    private float movementProgress;

    public boolean isShoot = false;

    public TankEntity(Transform transform) {
        super(transform);
        this.movementProgress = 0f;
        this.destinationTransform = transform;
    }

    public void setDestinationTransform(Transform transform) {
        this.destinationTransform = transform;
        this.transform.rotation = transform.rotation;
        this.movementProgress = 0f;
    }

    public void setDestinationPositionAsPosition(){
        this.transform.copyFromTransform(this.destinationTransform);
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
