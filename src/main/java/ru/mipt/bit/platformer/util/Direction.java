package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Random;

public class Direction {
    public final Transform transform;

    public static Direction Up = new Direction(new GridPoint2(0, 1), 90f);
    public static Direction Down = new Direction(new GridPoint2(0, -1), -90f);
    public static Direction Left = new Direction(new GridPoint2(-1, 0), 180f);
    public static Direction Right = new Direction(new GridPoint2(1, 0), 0f);

    public Direction(GridPoint2 transform, float rotation) {
        this.transform = new Transform(transform, rotation);
    }

    public Transform stepInTheDirection(Transform startTransform){
        GridPoint2 newPosition = new GridPoint2(
                this.transform.position.x + startTransform.position.x,
                this.transform.position.y + startTransform.position.y
        );

        return new Transform(newPosition, this.transform.rotation);
    }

    public static Direction getRandom(){
        Direction randomDirection = null;
        int rand = new Random().nextInt(4);
        switch (rand){
            case 0:
                randomDirection = Direction.Down;
                break;
            case 1:
                randomDirection = Direction.Left;
                break;
            case 2:
                randomDirection = Direction.Up;
                break;
            case 3:
                randomDirection = Direction.Right;
                break;
        }
        return randomDirection;
    }
    
    public static Direction fromRotation(float rotation){
        if(rotation == 0f){
            return Direction.Right;
        }

        if(rotation == 90f){
            return Direction.Up;
        }

        if(rotation == 180f){
            return Direction.Left;
        }

        if(rotation == -90f){
            return Direction.Down;
        }

        return null;
    }
}
