package ru.mipt.bit.platformer.level.dto;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.BulletEntity;
import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.gameobjects.BulletGameObject;
import ru.mipt.bit.platformer.gameobjects.TankGameObject;
import ru.mipt.bit.platformer.gameobjects.interfaces.IDynamicObject;
import ru.mipt.bit.platformer.gameobjects.interfaces.IGameObject;

import java.util.HashSet;

public class LevelBullet {
    private HashSet<IDynamicObject> gameObjects;


    public LevelBullet(){
        this.gameObjects = new HashSet<>();
    }

    public void addBullet(IDynamicObject gameObject) {
        this.gameObjects.add(gameObject);
    }

    public void removeBullet(IGameObject gameObject) {
        this.gameObjects.remove(gameObject);
    }

    public HashSet<IDynamicObject> getGameObjects() {
        return this.gameObjects;
    }
}
