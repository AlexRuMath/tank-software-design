package ru.mipt.bit.platformer.entity;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.controllers.IMove;
import ru.mipt.bit.platformer.level.ILevelObstacle;

public class PlayerEntity extends BaseEntity {
    public GridPoint2 destinitionPosition;
    public float movementProgress;

    private final IMove movement;

    public PlayerEntity(String pathToTexture, GridPoint2 position, float rotation, IMove movement) {
        super(pathToTexture, position, rotation);

        this.movementProgress = 0f;
        this.destinitionPosition = this.position;
        this.movement = movement;
    }

    public void getInput(Input input, ILevelObstacle levelObstacle){
        this.movement.move(this, input, levelObstacle);
    }
}
