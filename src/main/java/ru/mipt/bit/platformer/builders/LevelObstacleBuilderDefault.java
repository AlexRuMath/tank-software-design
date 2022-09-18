package ru.mipt.bit.platformer.builders;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.TreeEntity;
import ru.mipt.bit.platformer.level.ILevelObstacle;
import ru.mipt.bit.platformer.level.LevelObstacle;

public class LevelObstacleBuilderDefault implements ILevelObstacleBuilder {

    private ILevelObstacle levelObstacle;

    public LevelObstacleBuilderDefault(){
        this.levelObstacle = new LevelObstacle();
    }

    @Override
    public ILevelObstacleBuilder addTree(GridPoint2 position) {
        TreeEntity tree = new TreeEntity("images/greenTree.png", position, 0f);

        this.levelObstacle.addObstacle(tree);
        return this;
    }

    @Override
    public ILevelObstacle create() {
        ILevelObstacle result = this.levelObstacle;
        this.clear();

        return result;
    }

    @Override
    public void clear() {
        this.levelObstacle = new LevelObstacle();
    }
}
