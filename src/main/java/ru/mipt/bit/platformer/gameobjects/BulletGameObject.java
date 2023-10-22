package ru.mipt.bit.platformer.gameobjects;

import ru.mipt.bit.platformer.entity.BulletEntity;
import ru.mipt.bit.platformer.entity.ModelTexture;
import ru.mipt.bit.platformer.entity.interfaces.IGameEntity;
import ru.mipt.bit.platformer.entity.interfaces.IGun;
import ru.mipt.bit.platformer.entity.interfaces.IHealth;
import ru.mipt.bit.platformer.entity.interfaces.IMoveablePart;
import ru.mipt.bit.platformer.gameobjects.interfaces.IGameObject;
import ru.mipt.bit.platformer.gameobjects.interfaces.IDynamicObject;

public class BulletGameObject implements IGameObject, IDynamicObject {
    public final BulletEntity entity;
    public final ModelTexture modelTexture;

    public BulletGameObject(BulletEntity entity, ModelTexture modelTexture) {
        this.entity = entity;
        this.modelTexture = modelTexture;
    }

    @Override
    public IGameEntity getGameEntity() {
        return this.entity;
    }

    @Override
    public ModelTexture getModelTexture() {
        return this.modelTexture;
    }

    @Override
    public void disposeTexture() {
        this.modelTexture.texture.dispose();
    }

    @Override
    public IMoveablePart getMoveablePart() {
        return this.entity.moveablePart;
    }

    @Override
    public IHealth getHealth() {
        return this.entity.health;
    }

    @Override
    public IGun getGun() {
        return this.entity.owner.getGun();
    }

    @Override
    public void live(float deltaTime) {
    }
}
