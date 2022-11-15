package ru.mipt.bit.platformer.fabrics;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.gameobjects.BulletGameObject;
import ru.mipt.bit.platformer.gameobjects.TankGameObject;
import ru.mipt.bit.platformer.gameobjects.TreeGameObject;
import ru.mipt.bit.platformer.gameobjects.interfaces.IDynamicObject;
import ru.mipt.bit.platformer.util.Transform;

public interface IObjectFabric {
    TankGameObject createTank(Transform position, String pathToTexture);
    TreeGameObject createTree(Transform position, String pathToTexture);
    BulletGameObject createBullet(Transform position, String pathToTexture, IDynamicObject owner, Transform direction);
}
