package ru.mipt.bit.platformer.entity.interfaces;

public interface IGun {
    int getDamage();
    float getReloadTime();
    boolean isReload();
    boolean isServiceable();

    void crash();
    void fix();
    void reload();
    void continueReload(float deltaTime);
}
