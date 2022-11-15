package ru.mipt.bit.platformer.entity;

import ru.mipt.bit.platformer.entity.interfaces.IGameEntity;
import ru.mipt.bit.platformer.util.Transform;

public class TreeEntity implements IGameEntity {
    private final Transform transform;

    public TreeEntity(Transform transform) {
        this.transform = transform;
    }

    @Override
    public Transform getTransform() {
        return this.transform;
    }
}
