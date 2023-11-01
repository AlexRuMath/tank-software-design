package ru.mipt.bit.platformer.entity.parts;

import ru.mipt.bit.platformer.entity.interfaces.IHealth;

public class HealthEntity implements IHealth {
    private int health;
    private int maxHealth;

    public HealthEntity(int health) {
        this.maxHealth = health;
        this.health = health;
    }


    @Override
    public void updateHealth(int value) {
        this.health += value;
    }

    @Override
    public float getHealth() {
        return this.health;
    }

    @Override
    public float getMaxHealth() {
        return this.maxHealth;
    }

    @Override
    public boolean isLive() {
        return this.health > 0;
    }
}
