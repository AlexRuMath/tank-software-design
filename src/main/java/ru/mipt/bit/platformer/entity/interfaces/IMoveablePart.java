package ru.mipt.bit.platformer.entity.interfaces;

import ru.mipt.bit.platformer.util.Transform;

public interface IMoveablePart {
    float getSpeed();
    float getProgress();

    void continueProgress(float deltaTime);

    Transform getDestinationTransform();
    Transform getTransform();

    void setDestinationTransform(Transform transform);
    void setTransform(Transform transform);
    void setSpeed(float new_speed);
}
