package ru.mipt.bit.platformer.generators;

import ru.mipt.bit.platformer.builders.ILevelBuilder;
import ru.mipt.bit.platformer.builders.LevelBuilder;
import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.entity.TreeEntity;
import ru.mipt.bit.platformer.level.dto.IDataLevel;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.parser.ParseResult;
import ru.mipt.bit.platformer.util.Transform;

import java.util.List;

public class LevelDataFromFile implements IDataLevel {
    private ILevelBuilder builder;
    private ParseResult result;

    public LevelDataFromFile(ParseResult data){
        this.builder = new LevelBuilder();
        this.result = data;
    }

    @Override
    public Level createLevel() {
        List<Transform> trees = this.result.getList(TreeEntity.class);
        List<Transform> tanks = this.result.getList(TankEntity.class);

        for (Transform tree : trees) {
            this.builder.addTree(tree.position, "images/greenTree.png");
        }

        for (Transform tank : tanks) {
            this.builder.addTank(tank.position, "images/tank_red.png");
        }

        Transform transform = this.result.getPosition();
        this.builder.addPlayer(transform.position, "images/tank_blue.png");
        this.builder.setWidth(result.width);
        this.builder.setHeight(result.height);

        return this.builder.create();
    }
}
