package ru.mipt.bit.platformer.entity;

import ru.mipt.bit.platformer.entity.interfaces.IGameEntity;
import ru.mipt.bit.platformer.entity.interfaces.IGun;
import ru.mipt.bit.platformer.entity.interfaces.IHealth;
import ru.mipt.bit.platformer.entity.interfaces.IMoveablePart;
import ru.mipt.bit.platformer.states.IState;
import ru.mipt.bit.platformer.util.Transform;

    public class TankEntity implements IGameEntity {
    public final IHealth health;
    public final IMoveablePart movePart;
    public final IGun gun;

    private IState currentState;

    public TankEntity(IState initState, IHealth health, IMoveablePart movePart, IGun gun) {
        this.health = health;
        this.movePart = movePart;
        this.gun = gun;
        this.currentState = initState;
    }

    public void setState(IState state){
        state.configureParameters(this);
    }

    @Override
    public Transform getTransform() {
        return this.movePart.getTransform();
    }
}
