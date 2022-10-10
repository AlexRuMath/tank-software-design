package ru.mipt.bit.platformer.controllers;

import com.badlogic.gdx.Input;
import ru.mipt.bit.platformer.util.Direction;

import static com.badlogic.gdx.Input.Keys.*;

public class DirectionMoveController implements IMoveController {
    @Override
    public Direction getDirection(Input input) {
        Direction resultDirection = null;

        if (input.isKeyPressed(UP) || input.isKeyPressed(W)) {
            resultDirection = Direction.Up;
        }
        if (input.isKeyPressed(LEFT) || input.isKeyPressed(A)) {
            resultDirection = Direction.Left;
        }
        if (input.isKeyPressed(DOWN) || input.isKeyPressed(S)) {
            resultDirection = Direction.Down;
        }
        if (input.isKeyPressed(RIGHT) || input.isKeyPressed(D)) {
            resultDirection = Direction.Right;
        }

        return resultDirection;
    }
}
