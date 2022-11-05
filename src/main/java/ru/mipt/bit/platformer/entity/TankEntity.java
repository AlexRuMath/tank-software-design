package ru.mipt.bit.platformer.entity;

import ru.mipt.bit.platformer.util.Transform;

public class TankEntity extends BaseEntity implements IMoveEntity {
    private Transform destinationTransform;

    private float movementProgress;

    public TankEntity(Transform transform, ModelTexture modelTexture) {
        super(transform, modelTexture);
        this.movementProgress = 0f;
        this.destinationTransform = transform;
    }

    public void setDestinationTransform(Transform transform) {
        this.destinationTransform = transform;
        this.transform.setRotation(transform.getRotation());
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
            throw new IllegalArgumentException("The movement progress does not belong to segment [0;1]: " + movementProgress);

        this.movementProgress = movementProgress;
    }
}
