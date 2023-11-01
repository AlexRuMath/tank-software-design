package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.entity.ModelTexture;
import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.entity.interfaces.IGameEntity;
import ru.mipt.bit.platformer.entity.interfaces.IGun;
import ru.mipt.bit.platformer.entity.interfaces.IHealth;
import ru.mipt.bit.platformer.entity.interfaces.IMoveablePart;
import ru.mipt.bit.platformer.gameobjects.interfaces.IDynamicObject;
import ru.mipt.bit.platformer.gameobjects.interfaces.IGameObject;

import java.util.ArrayList;
import java.util.List;

import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class TankGameObject implements IGameObject, IDynamicObject {
    public final TankEntity entity;
    public final ModelTexture texture;
    public List<IDynamicObject> components;

    public TankGameObject(TankEntity entity, ModelTexture texture) {
        this.entity = entity;
        this.texture = texture;
        this.components = new ArrayList<>();
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
    public void draw(Batch batch) {
        drawTextureRegionUnscaled(batch, this);
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
        return this.entity.gun;
    }

    @Override
    public void live(float deltaTime) {
        this.entity.gun.continueReload(deltaTime);
    }
}
