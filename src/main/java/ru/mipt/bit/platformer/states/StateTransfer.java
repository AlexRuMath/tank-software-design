package ru.mipt.bit.platformer.states;

import ru.mipt.bit.platformer.entity.interfaces.IHealth;

public class StateTransfer {
    public static IState generateState(IHealth health){
        float currentHealth = health.getHealth();
        float maxHealth = health.getMaxHealth();

        if(currentHealth > maxHealth * 0.15f && currentHealth < maxHealth * 0.7f){
            return new MiddleState();
        }

        if(currentHealth < maxHealth * 0.15f){
            return new HardState();
        }

        return new EasyState();
    }
}
