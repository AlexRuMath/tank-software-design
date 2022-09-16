package ru.mipt.bit.platformer.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class BaseEntity {
    public Texture texture;
    public TextureRegion textureRegion;
    public GridPoint2 position;
    public Rectangle rectangle;
    public float rotation;

    public BaseEntity(String pathToTexture,
                      GridPoint2 position,
                      float rotation) {
        this.texture = new Texture(pathToTexture);
        this.textureRegion = new TextureRegion(this.texture);
        this.rectangle = createBoundingRectangle(this.textureRegion);
        this.position = position;
        this.rotation = rotation;
    }

    public void draw(Batch batch){
        drawTextureRegionUnscaled(batch, this.textureRegion, this.rectangle, this.rotation);
    }
}
