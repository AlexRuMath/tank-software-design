package ru.mipt.bit.platformer.states;

import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.entity.interfaces.IMoveablePart;

public interface IState {
    void configureParameters(TankEntity entity);
}
