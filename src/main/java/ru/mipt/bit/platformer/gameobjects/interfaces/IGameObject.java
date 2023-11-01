package ru.mipt.bit.platformer.gameobjects.interfaces;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.entity.ModelTexture;
import ru.mipt.bit.platformer.entity.interfaces.IGameEntity;

import java.util.List;

public interface IGameObject {
    IGameEntity getGameEntity();
    ModelTexture getModelTexture();

    void draw(Batch batch);
    void disposeTexture();
}