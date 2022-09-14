package ru.mipt.bit.platformer.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.HashMap;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getLayerByName;

public class Level {

    private static Level instance;
    public TiledMap tiledMap;
    public MapRenderer levelRenderer;

    public HashMap<String, TileMovement> mapMovements;

    private Level() {
        this.mapMovements = new HashMap<>();
    }

    public static Level getInstance(){
        if(instance == null){
            instance = new Level();
        }
        return instance;
    }


    public void setLevelScheme(String pathToScheme){
        this.tiledMap = new TmxMapLoader().load(pathToScheme);
    }

    public void setLevelRender(Batch batch){
        this.levelRenderer = createSingleLayerMapRenderer(this.tiledMap, batch);
    }

    public void addLayerMovement(String nameLayer, Interpolation interpolation){
        TiledMapTileLayer layer = getLayerByName(this.tiledMap, nameLayer);
        TileMovement movement = new TileMovement(layer, interpolation);
        this.mapMovements.put(nameLayer, movement);
    }

    public void render(){
        this.levelRenderer.render();
    }
}
