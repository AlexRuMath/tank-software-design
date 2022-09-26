package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.math.GridPoint2;

public class Direction {
    private final Transform direction;

    public static Direction Up = new Direction(new GridPoint2(0, 1), 90f);
    public static Direction Down = new Direction(new GridPoint2(0, -1), -90f);
    public static Direction Left = new Direction(new GridPoint2(-1, 0), 180f);
    public static Direction Right = new Direction(new GridPoint2(1, 0), 0f);
    public static Direction Empty = new Direction(new GridPoint2(0, 0), 0f);

    public Direction(GridPoint2 direction, float rotation) {
        this.direction = new Transform(direction, rotation);
    }

    public Transform stepInTheDirection(Transform startTransform){
        GridPoint2 newPosition = new GridPoint2(
                this.direction.getPosition().x + startTransform.getPosition().x,
                this.direction.getPosition().y + startTransform.getPosition().y
        );

        return new Transform(newPosition, this.direction.getRotation());
    }
}
