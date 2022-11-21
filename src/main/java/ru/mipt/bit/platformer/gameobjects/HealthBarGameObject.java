package ru.mipt.bit.platformer.gameobjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.entity.ModelTexture;
import ru.mipt.bit.platformer.entity.interfaces.IGameEntity;
import ru.mipt.bit.platformer.entity.interfaces.IGun;
import ru.mipt.bit.platformer.entity.interfaces.IHealth;
import ru.mipt.bit.platformer.entity.interfaces.IMoveablePart;
import ru.mipt.bit.platformer.gameobjects.interfaces.IDynamicObject;

import java.util.List;

import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class HealthBarGameObject implements IDynamicObject {

    private final IDynamicObject owner;
    private final ModelTexture texture;

    public HealthBarGameObject(IDynamicObject owner, ModelTexture texture) {
        this.owner = owner;
        this.texture = texture;
    }


    @Override
    public IGameEntity getGameEntity() {
        return this.owner.getGameEntity();
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
        return null;
    }

    @Override
    public IHealth getHealth() {
        return null;
    }

    @Override
    public IGun getGun() {
        return null;
    }

    @Override
    public void live(float deltaTime) {

    }
}
