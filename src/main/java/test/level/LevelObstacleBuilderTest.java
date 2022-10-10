package test.level;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.Assert;
import org.junit.Test;
import ru.mipt.bit.platformer.builders.ILevelObstacleBuilder;
import ru.mipt.bit.platformer.builders.LevelObstacleBuilderDefault;
import ru.mipt.bit.platformer.entity.BaseEntity;
import ru.mipt.bit.platformer.level.ILevelObstacle;

import java.util.HashSet;
import java.util.Set;

public class LevelObstacleBuilderTest extends Assert {

    @Test
    public void CreateObsctacleLevel(){
        ILevelObstacleBuilder builder = new LevelObstacleBuilderDefault();

        builder.addTree(new GridPoint2(1, 2));
        builder.addTree(new GridPoint2(3, 4));
        builder.addTree(new GridPoint2(5, 6));
        builder.addTree(new GridPoint2(7, 8));
        builder.addTree(new GridPoint2(9, 10));

        ILevelObstacle levelObstacle = builder.create();

        assertNotNull(levelObstacle);
    }
}
