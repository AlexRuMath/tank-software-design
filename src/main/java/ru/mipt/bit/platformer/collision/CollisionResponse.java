package ru.mipt.bit.platformer.collision;

import ru.mipt.bit.platformer.entity.BaseEntity;
import ru.mipt.bit.platformer.entity.interfaces.IMoveEntity;

public class CollisionResponse {
    public CollisionType type;
    public IMoveEntity firstObject;
    public BaseEntity secondObject;

    public CollisionResponse(CollisionType type, IMoveEntity firstObject, BaseEntity secondObject) {
        this.type = type;
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }
}
