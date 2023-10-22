package ru.mipt.bit.platformer.entity.interfaces;

public interface IHealth {
    void updateHealth(int value);
    int getHealth();
    boolean isLive();
}
