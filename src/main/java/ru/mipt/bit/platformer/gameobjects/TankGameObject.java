package ru.mipt.bit.platformer.gameobjects;

import ru.mipt.bit.platformer.entity.ModelTexture;
import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.entity.interfaces.IGameEntity;
import ru.mipt.bit.platformer.entity.interfaces.IGun;
import ru.mipt.bit.platformer.entity.interfaces.IHealth;
import ru.mipt.bit.platformer.entity.interfaces.IMoveablePart;
import ru.mipt.bit.platformer.gameobjects.interfaces.IDynamicObject;
import ru.mipt.bit.platformer.gameobjects.interfaces.IGameObject;

public class TankGameObject implements IGameObject, IDynamicObject {
    public final TankEntity entity;
    public final ModelTexture texture;

    public TankGameObject(TankEntity entity, ModelTexture texture) {
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

    @Override
    public IMoveablePart getMoveablePart() {
        return this.entity.movePart;
    }

    @Override
    public IHealth getHealth() {
        return this.entity.health;
    }

    @Override
    public IGun getGun() {
        return this.entity.gunPart;
    }

    @Override
    public void live(float deltaTime) {
        this.entity.gunPart.continueReload(deltaTime);
    }
}
