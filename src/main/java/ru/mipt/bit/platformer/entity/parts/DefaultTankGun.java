package ru.mipt.bit.platformer.entity.parts;

import ru.mipt.bit.platformer.entity.interfaces.IGun;

public class DefaultTankGun implements IGun {
    private final float reloadTime;
    private float currentReloadTime;
    private final int damage;

    public DefaultTankGun(int damage, float reloadTime) {
        this.reloadTime = reloadTime;
        this.currentReloadTime = 0f;
        this.damage = damage;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public float getReloadTime() {
        return reloadTime;
    }

    @Override
    public boolean isReload() {
        return currentReloadTime != 0f;
    }

    @Override
    public void reload() {
        this.currentReloadTime = this.reloadTime;
    }

    @Override
    public void continueReload(float deltaTime) {
        if(!this.isReload()) return;

        this.currentReloadTime -= deltaTime;
        if(this.currentReloadTime <= 0f){
            this.currentReloadTime = 0f;
        }
    }
}
