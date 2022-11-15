package ru.mipt.bit.platformer.gameobjects;

import ru.mipt.bit.platformer.entity.BaseEntity;
import ru.mipt.bit.platformer.entity.ModelTexture;
import ru.mipt.bit.platformer.entity.TreeEntity;
import ru.mipt.bit.platformer.entity.interfaces.IGameEntity;
import ru.mipt.bit.platformer.gameobjects.interfaces.IGameObject;

public class TreeGameObject implements IGameObject {
    public final TreeEntity entity;
    public final ModelTexture texture;

    public TreeGameObject(TreeEntity entity, ModelTexture texture) {
        this.entity = entity;
        this.texture = texture;
    }

    @Override
    public IGameEntity getGameEntity() {
        return this.entity;
    }

    @Override
    public ModelTexture getModelTexture() {
        return this.texture;
    }

    @Override
    public void disposeTexture() {
        this.texture.texture.dispose();
    }
}
