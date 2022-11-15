package ru.mipt.bit.platformer.entity.interfaces;

public interface IGun {
    int getDamage();
    float getReloadTime();
    boolean isReload();
    void reload();
    void continueReload(float deltaTime);
}
