package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Random;

public class Transform {
    public GridPoint2 position;
    public float rotation;

    public static Transform Up = new Transform(new GridPoint2(0, 1), 90f);
    public static Transform Down = new Transform(new GridPoint2(0, -1), -90f);
    public static Transform Left = new Transform(new GridPoint2(-1, 0), 180f);
    public static Transform Right = new Transform(new GridPoint2(1, 0), 0f);
    public static Transform Empty = new Transform(new GridPoint2(0, 0), 0f);

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

    public Transform stepInTheDirection(Transform startTransform){
        GridPoint2 newPosition = new GridPoint2(
                this.position.x + startTransform.position.x,
                this.position.y + startTransform.position.y
        );

        return new Transform(newPosition, this.rotation);
    }

    public static Transform valueOf(Transform transform){
        return new Transform(transform.position, transform.rotation);
    }

    public static Transform getRandom(){
        Transform randomTransform = null;
        int rand = new Random().nextInt(4);
        switch (rand){
            case 0:
                randomTransform = Transform.Down;
                break;
            case 1:
                randomTransform = Transform.Left;
                break;
            case 2:
                randomTransform = Transform.Up;
                break;
            case 3:
                randomTransform = Transform.Right;
                break;
        }
        return randomTransform;
    }

    public static Transform fromRotation(float rotation){
        if(rotation == 0f){
            return Transform.Right;
        }

        if(rotation == 90f){
            return Transform.Up;
        }

        if(rotation == 180f){
            return Transform.Left;
        }

        if(rotation == -90f){
            return Transform.Down;
        }

        return Transform.Empty;
    }
}
