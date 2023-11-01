package ru.mipt.bit.platformer.entity.interfaces;

public interface IHealth {
    void updateHealth(int value);
    float getHealth();
    float getMaxHealth();
    boolean isLive();
}
