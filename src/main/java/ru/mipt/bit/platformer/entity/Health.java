package ru.mipt.bit.platformer.entity;

import ru.mipt.bit.platformer.entity.interfaces.IHealth;

public class Health implements IHealth {
    private int health;

    public Health(int health) {
        this.health = health;
    }


    @Override
    public void updateHealth(int value) {
        this.health += value;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public boolean isLive() {
        return this.health > 0;
    }
}
