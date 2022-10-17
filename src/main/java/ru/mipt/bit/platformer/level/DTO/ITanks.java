package ru.mipt.bit.platformer.level.DTO;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.gameobjects.TankGameObject;
import ru.mipt.bit.platformer.util.Transform;

import java.util.HashSet;

public interface ITanks {
    void addTank(TankGameObject gameObject);
    void removeTank(TankGameObject gameObject);

    HashSet<TankGameObject> getGameObjects();
    HashSet<GridPoint2> getPositions();
}
