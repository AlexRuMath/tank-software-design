package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.math.GridPoint2;

public class Transform {
    private GridPoint2 position;
    private float rotation;

    public Transform(GridPoint2 position, float rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public void copyFromTransform(Transform transform) {
        this.position = new GridPoint2(transform.getPosition());
        this.rotation = transform.getRotation();
    }

    public GridPoint2 getPosition() {
        return position;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
