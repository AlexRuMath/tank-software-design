package ru.mipt.bit.platformer.level.dto;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.BulletEntity;
import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.gameobjects.BulletGameObject;
import ru.mipt.bit.platformer.gameobjects.TankGameObject;
import ru.mipt.bit.platformer.gameobjects.interfaces.IGameObject;

import java.util.HashSet;

public class LevelBullet {
    private HashSet<GridPoint2> positions;

    private HashSet<BulletGameObject> gameObjects;


    public LevelBullet(){
        this.gameObjects = new HashSet<>();
        this.positions = new HashSet<>();
    }

    public void addBullet(BulletGameObject gameObject) {
        this.gameObjects.add(gameObject);
        this.positions.add(gameObject.entity.transform.position);
    }

    public void removeBullet(BulletGameObject gameObject) {
        this.gameObjects.remove(gameObject);
        this.positions.remove(gameObject.entity.transform.position);
    }

    public void removeByEntity(BulletEntity entity){
        for (BulletGameObject bulletGameObject: this.gameObjects){
            if(bulletGameObject.entity == entity){
                this.gameObjects.remove(bulletGameObject);
                break;
            }
        }
    }

    public HashSet<BulletGameObject> getGameObjects() {
        return this.gameObjects;
    }
}
