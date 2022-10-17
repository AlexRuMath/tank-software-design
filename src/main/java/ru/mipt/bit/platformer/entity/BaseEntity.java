package ru.mipt.bit.platformer.entity;

import ru.mipt.bit.platformer.util.Transform;

public abstract class BaseEntity {
    public final Transform transform;

    protected BaseEntity(Transform transform) {
        this.transform = transform;
    }
}
