package ru.mipt.bit.platformer.gameobjects.interfaces;

import ru.mipt.bit.platformer.entity.ModelTexture;
import ru.mipt.bit.platformer.entity.interfaces.IGameEntity;

public interface IGameObject {
    IGameEntity getGameEntity();
    ModelTexture getModelTexture();

    void disposeTexture();
}
