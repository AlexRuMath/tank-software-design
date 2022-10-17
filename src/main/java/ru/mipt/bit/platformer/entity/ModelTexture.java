package ru.mipt.bit.platformer.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public class ModelTexture {
    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;

    public ModelTexture(String pathToTexture) {
        this.texture = new Texture(pathToTexture);
        this.textureRegion = new TextureRegion(this.texture);
        this.rectangle = createBoundingRectangle(this.textureRegion);
    }

    public Texture getTexture(){
        return this.texture;
    }

    public TextureRegion getTextureRegion(){
        return this.textureRegion;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
