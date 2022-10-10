package ru.mipt.bit.platformer.directors;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.builders.ILevelObstacleBuilder;
import ru.mipt.bit.platformer.builders.LevelObstacleBuilderDefault;
import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.entity.TreeEntity;
import ru.mipt.bit.platformer.level.IFillLevel;
import ru.mipt.bit.platformer.level.ILevelObstacle;
import ru.mipt.bit.platformer.parser.ParseResult;
import ru.mipt.bit.platformer.util.Transform;

import java.util.List;

public class LevelFromFile implements IFillLevel {
    private ILevelObstacleBuilder builder;
    private ParseResult result;
    private GridPoint2 playerPosition;

    public LevelFromFile(ParseResult data){
        this.builder = new LevelObstacleBuilderDefault();
        this.result = data;
    }

    @Override
    public ILevelObstacle fill() {
        List<Transform> trees = this.result.getList(TreeEntity.class);
        List<Transform> tanks = this.result.getList(TankEntity.class);

        for (Transform tree : trees) {
            this.builder.addTree(tree.getPosition());
        }

        playerPosition = tanks.get(0).getPosition();

        return this.builder.create();
    }

    @Override
    public GridPoint2 getPlayerPosition() {
        return this.playerPosition;
    }
}
