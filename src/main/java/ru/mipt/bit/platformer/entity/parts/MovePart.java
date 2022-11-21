package ru.mipt.bit.platformer.entity.parts;

import ru.mipt.bit.platformer.entity.interfaces.IMoveablePart;
import ru.mipt.bit.platformer.util.Transform;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class MovePart implements IMoveablePart {
    private float speed;
    private float progress;
    private Transform destinationPos;
    private Transform currentPos;

    public MovePart(float speed, float progress, Transform destinationPos, Transform currentPos) {
        this.speed = speed;
        this.progress = progress;
        this.destinationPos = destinationPos;
        this.currentPos = currentPos;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getProgress() {
        return this.progress;
    }

    @Override
    public void continueProgress(float deltaTime) {
        this.progress = ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress(this.progress, deltaTime, this.speed);
        if(isEqual(this.progress,1f)){
            this.setTransform(this.destinationPos);
        }
    }

    @Override
    public Transform getDestinationTransform() {
        return this.destinationPos;
    }

    @Override
    public Transform getTransform() {
        return this.currentPos;
    }

    @Override
    public void setDestinationTransform(Transform transform) {
        this.destinationPos.copyFromTransform(transform);
        this.currentPos.rotation = transform.rotation;
        this.progress = 0f;
    }

    @Override
    public void setTransform(Transform transform) {
        this.currentPos.copyFromTransform(transform);
    }

    @Override
    public void setSpeed(float new_speed) {
        this.speed = new_speed;
    }
}
