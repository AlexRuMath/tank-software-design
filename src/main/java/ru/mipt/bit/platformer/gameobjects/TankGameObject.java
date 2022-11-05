package ru.mipt.bit.platformer.gameobjects;

import ru.mipt.bit.platformer.entity.BaseEntity;
import ru.mipt.bit.platformer.entity.IMoveEntity;
import ru.mipt.bit.platformer.entity.ModelTexture;
import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.util.Transform;

public class TankGameObject implements IGameObject {
    public final TankEntity entity;
    public final ModelTexture texture;

    public TankGameObject(TankEntity entity, ModelTexture texture) {
        this.entity = entity;
        this.texture = texture;
    }

    @Override
    public BaseEntity getEntity() {
        return this.entity;
    }

    @Override
    public ModelTexture getModelTexture() {
        return this.texture;
    }
}
