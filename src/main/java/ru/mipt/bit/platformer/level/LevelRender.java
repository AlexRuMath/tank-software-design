package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.entity.interfaces.IGameEntity;
import ru.mipt.bit.platformer.entity.interfaces.IMoveablePart;
import ru.mipt.bit.platformer.entity.ModelTexture;
import ru.mipt.bit.platformer.gameobjects.interfaces.IGameObject;
import ru.mipt.bit.platformer.level.dto.ILevelObstacle;
import ru.mipt.bit.platformer.util.TileMovement;
import ru.mipt.bit.platformer.util.Transform;

import java.util.HashMap;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class LevelRender {

    private static LevelRender instance;
    private TiledMap tiledMap;
    private MapRenderer levelRenderer;
    private HashMap<String, TileMovement> mapMovements;

    private LevelRender() {
        this.mapMovements = new HashMap<>();
    }

    public static LevelRender getInstance() {
        if (instance == null) {
            instance = new LevelRender();
        }
        return instance;
    }

    public void setObstacle(ILevelObstacle levelObstacle) {
        TiledMapTileLayer groundLayer = getSingleLayer(this.tiledMap);

        for (IGameObject gameObject : levelObstacle.getGameObjects()) {
            ModelTexture texture = gameObject.getModelTexture();
            IGameEntity entity = gameObject.getGameEntity();

            moveRectangleAtTileCenter(groundLayer, texture.rectangle, entity.getTransform().position);
        }
    }

    public void setLevelScheme(String pathToScheme) {
        this.tiledMap = new TmxMapLoader().load(pathToScheme);
    }

    public void setLayerRenderer(Batch batch) {
        this.levelRenderer = createSingleLayerMapRenderer(this.tiledMap, batch);
    }

    public void setLayerMovement(String nameLayer, Interpolation interpolation) {
        TiledMapTileLayer layer = getLayerByName(this.tiledMap, nameLayer);
        TileMovement movement = new TileMovement(layer, interpolation);
        this.mapMovements.put(nameLayer, movement);
    }

    public void moveRectangle(String nameLayer, Rectangle rectangle, IMoveablePart movablePart){
        this.mapMovements.get(nameLayer).moveRectangleBetweenTileCenters(rectangle, movablePart);
    }

    public void renderLevelObject(Level level, Batch batch){
        drawModel(level.playerTank, batch);

        for (IGameObject gameObject : level.levelBullets.getGameObjects()) {
            drawModel(gameObject, batch);
        }

        for (IGameObject gameObject : level.levelObstacle.getGameObjects()) {
            drawModel(gameObject, batch);
        }

        for (IGameObject gameObject : level.levelTanks.getGameObjects()) {
            drawModel(gameObject, batch);
        }
    }

    private void drawModel(IGameObject gameObject, Batch batch) {
        ModelTexture texture = gameObject.getModelTexture();
        Transform transform = gameObject.getGameEntity().getTransform();

        drawTextureRegionUnscaled(batch, texture.textureRegion,
                                         texture.rectangle,
                                         transform.rotation);
    }

    public void dispose(){
        this.tiledMap.dispose();
    }

    public int getHeight() {
        return (int) this.tiledMap.getProperties().get("height");
    }

    public int getWidth() {
        return (int) this.tiledMap.getProperties().get("width");
    }

    public void render() {
        this.levelRenderer.render();
    }
}
