package ru.mipt.bit.platformer.states;

import ru.mipt.bit.platformer.entity.TankEntity;

public class MiddleState implements IState {
    private final float speedRatio = 2f;

    @Override
    public void configureParameters(TankEntity entity) {
        float newSpeed = entity.movePart.getSpeed() * speedRatio;
        entity.movePart.setSpeed(newSpeed);
    }
}
