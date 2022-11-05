package ru.mipt.bit.platformer.entity;

import ru.mipt.bit.platformer.util.Transform;

public abstract class BaseEntity {
    public final Transform transform;
    public final ModelTexture modelTexture;

    protected BaseEntity(Transform transform, ModelTexture modelTexture) {
        this.transform = transform;
        this.modelTexture = modelTexture;
    }
}
