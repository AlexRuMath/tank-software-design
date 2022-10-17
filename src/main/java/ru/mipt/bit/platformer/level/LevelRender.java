package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.entity.BaseEntity;
import ru.mipt.bit.platformer.entity.ModelTexture;
import ru.mipt.bit.platformer.gameobjects.IGameObject;
import ru.mipt.bit.platformer.level.DTO.ILevelObstacle;
import ru.mipt.bit.platformer.level.DTO.ITanks;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.HashMap;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class LevelRender {

    private static LevelRender instance;
    public TiledMap tiledMap;
    public MapRenderer levelRenderer;
    public HashMap<String, TileMovement> mapMovements;

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
            BaseEntity entity = gameObject.getEntity();

            moveRectangleAtTileCenter(groundLayer, texture.getRectangle(), entity.transform.getPosition());
        }
    }

    public void renderTanks(ITanks levelTanks){
        TiledMapTileLayer groundLayer = getSingleLayer(this.tiledMap);

        for(IGameObject gameObject: levelTanks.getGameObjects()){
            ModelTexture texture = gameObject.getModelTexture();
            BaseEntity entity = gameObject.getEntity();

            moveRectangleAtTileCenter(groundLayer, texture.getRectangle(), entity.transform.getPosition());
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
