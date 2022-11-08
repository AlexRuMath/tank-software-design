package ru.mipt.bit.platformer.gameobjects;

import ru.mipt.bit.platformer.entity.BaseEntity;
import ru.mipt.bit.platformer.entity.BulletEntity;
import ru.mipt.bit.platformer.entity.ModelTexture;
import ru.mipt.bit.platformer.gameobjects.interfaces.IGameObject;

public class BulletGameObject implements IGameObject {
    public final BulletEntity entity;
    public final ModelTexture modelTexture;

    public BulletGameObject(BulletEntity entity, ModelTexture modelTexture) {
        this.entity = entity;
        this.modelTexture = modelTexture;
    }

    @Override
    public BaseEntity getEntity() {
        return this.entity;
    }

    @Override
    public ModelTexture getModelTexture() {
        return this.modelTexture;
    }
}
