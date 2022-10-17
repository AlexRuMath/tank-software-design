package ru.mipt.bit.platformer.gameobjects;

import ru.mipt.bit.platformer.entity.BaseEntity;
import ru.mipt.bit.platformer.entity.ModelTexture;

public interface IGameObject {
    BaseEntity getEntity();
    ModelTexture getModelTexture();
}
