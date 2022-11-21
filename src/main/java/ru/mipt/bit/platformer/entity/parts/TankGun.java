package ru.mipt.bit.platformer.entity.parts;

import ru.mipt.bit.platformer.entity.interfaces.IGun;

public class TankGun implements IGun {
    private final int damage;
    private final float reloadTime;
    private float currentReloadTime;

    public boolean isServiceable;

    public TankGun(int damage, float reloadTime) {
        this.reloadTime = reloadTime;
        this.currentReloadTime = 0f;
        this.damage = damage;
        this.isServiceable = true;
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
    public boolean isServiceable() {
        return this.isServiceable;
    }

    @Override
    public void crash() {
        this.isServiceable = false;
    }

    @Override
    public void fix() {
        this.isServiceable = true;
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
