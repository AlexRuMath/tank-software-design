package ru.mipt.bit.platformer.controllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.PlayerEntity;
import ru.mipt.bit.platformer.level.ILevelObstacle;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class PlayerMoveController implements IMove{
    @Override
    public void move(PlayerEntity playerEntity, Input input, ILevelObstacle levelObstacle) {
        if(!isEqual(playerEntity.movementProgress, 1f)) return;
        GridPoint2 destinationPosition = playerEntity.position;

        if (input.isKeyPressed(UP) || input.isKeyPressed(W)) {
            destinationPosition = incrementedY(playerEntity.position);
            playerEntity.rotation = 90f;
        }
        if (input.isKeyPressed(LEFT) || input.isKeyPressed(A)) {
            destinationPosition = decrementedX(playerEntity.position);
            playerEntity.rotation = -180f;
        }
        if (input.isKeyPressed(DOWN) || input.isKeyPressed(S)) {
            destinationPosition = decrementedY(playerEntity.position);
            playerEntity.rotation = -90f;
        }
        if (input.isKeyPressed(RIGHT) || input.isKeyPressed(D)) {
            destinationPosition = incrementedX(playerEntity.position);
            playerEntity.rotation = 0f;
        }

        boolean isObstaclePosition = levelObstacle.getPositions().contains(destinationPosition);
        if (!isObstaclePosition) {
            playerEntity.destinationPosition = destinationPosition;
            playerEntity.movementProgress = 0f;
        }
    }
}
