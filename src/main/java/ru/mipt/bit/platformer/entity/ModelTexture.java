package ru.mipt.bit.platformer.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public class ModelTexture {
    public final Texture texture;
    public final TextureRegion textureRegion;
    public final Rectangle rectangle;

    public ModelTexture(String pathToTexture) {
        this.texture = new Texture(pathToTexture);
        this.textureRegion = new TextureRegion(this.texture);
        this.rectangle = createBoundingRectangle(this.textureRegion);
    }
}
