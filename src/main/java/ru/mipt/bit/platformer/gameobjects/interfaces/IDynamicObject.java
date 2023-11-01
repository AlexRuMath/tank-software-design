package ru.mipt.bit.platformer.gameobjects.interfaces;

import ru.mipt.bit.platformer.entity.interfaces.IGun;
import ru.mipt.bit.platformer.entity.interfaces.IHealth;
import ru.mipt.bit.platformer.entity.interfaces.IMoveablePart;

public interface IDynamicObject extends IGameObject {
    IMoveablePart getMoveablePart();
    IHealth getHealth();
    IGun getGun();

    void live(float deltaTime);
}
