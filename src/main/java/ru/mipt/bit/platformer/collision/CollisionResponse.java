package ru.mipt.bit.platformer.collision;

import ru.mipt.bit.platformer.gameobjects.interfaces.IDynamicObject;
import ru.mipt.bit.platformer.gameobjects.interfaces.IGameObject;

public class CollisionResponse {
    public CollisionType type;
    public IDynamicObject dynamicObject;
    public IGameObject collisionObject;

    public CollisionResponse(CollisionType type, IDynamicObject dynamicObject, IGameObject collisionObject) {
        this.type = type;
        this.dynamicObject = dynamicObject;
        this.collisionObject = collisionObject;
    }
}
