package ru.mipt.bit.platformer.builders;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.ModelTexture;
import ru.mipt.bit.platformer.util.Transform;
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
        ModelTexture treeTexture = new ModelTexture("images/greenTree.png");
        Transform treeTransform = new Transform(position, 0f);
        TreeEntity tree = new TreeEntity(treeTransform, treeTexture);

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
