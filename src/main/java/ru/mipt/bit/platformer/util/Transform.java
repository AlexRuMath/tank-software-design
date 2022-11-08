package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.math.GridPoint2;

public class Transform {
    public GridPoint2 position;
    public float rotation;

    public Transform(GridPoint2 position, float rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public Transform(GridPoint2 position) {
        this.position = position;
        this.rotation = 0f;
    }

    public void copyFromTransform(Transform transform) {
        this.position = new GridPoint2(transform.position);
        this.rotation = transform.rotation;
    }

    public static Transform valueOf(Transform transform){
        return new Transform(transform.position, transform.rotation);
    }

    public Direction getDirection(){
        return new Direction(this.position, this.rotation);
    }
}
