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
        GridPoint2 destinitionPos = playerEntity.position;

        if (input.isKeyPressed(UP) || input.isKeyPressed(W)) {
            destinitionPos = incrementedY(playerEntity.position);
            playerEntity.rotation = 90f;
        }
        if (input.isKeyPressed(LEFT) || input.isKeyPressed(A)) {
            destinitionPos = decrementedX(playerEntity.position);
            playerEntity.rotation = -180f;
        }
        if (input.isKeyPressed(DOWN) || input.isKeyPressed(S)) {
            destinitionPos = decrementedY(playerEntity.position);
            playerEntity.rotation = -90f;
        }
        if (input.isKeyPressed(RIGHT) || input.isKeyPressed(D)) {
            destinitionPos = incrementedX(playerEntity.position);
            playerEntity.rotation = 0f;
        }

        boolean isObstaclePosition = levelObstacle.getPositions().contains(destinitionPos);
        if (!isObstaclePosition) {
            playerEntity.destinitionPosition = destinitionPos;
            playerEntity.movementProgress = 0f;
        }
    }
}
