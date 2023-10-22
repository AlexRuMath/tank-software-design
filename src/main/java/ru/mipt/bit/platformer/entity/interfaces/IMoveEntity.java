package ru.mipt.bit.platformer.entity.interfaces;

import ru.mipt.bit.platformer.util.Transform;

public interface IMoveEntity {
    Transform getTransform();
    Transform getDestinationTransform();
    float getMovementProgress();
    void setDestinationTransform(Transform destination);
    void setMovementProgress(float movementProgress);
    void setDestinationTransformAsCurrentTransform();
    float getMovementSpeed();
}
