package ru.mipt.bit.platformer.generators;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.builders.ILevelBuilder;
import ru.mipt.bit.platformer.builders.LevelBuilder;
import ru.mipt.bit.platformer.level.DTO.IDataLevel;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.util.Transform;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomLevel implements IDataLevel {
    private ILevelBuilder levelBuilder;
    private Random random;
    private int countObstacle = 10;
    private int width = 0;
    private int height = 0;

    private Transform playerPosition;

    public RandomLevel(int x, int y, int countObstacle) {
        this.countObstacle = countObstacle;
        this.levelBuilder = new LevelBuilder();
        this.random = new Random();
        this.width = x;
        this.height = y;
    }

    public RandomLevel(int x, int y) {
        this.levelBuilder = new LevelBuilder();
        this.random = new Random();
        this.width = x;
        this.height = y;
    }

    public Level createLevel() {
        Set<GridPoint2> treeSet = new HashSet<>();

        for (int i = 0; i < this.countObstacle; i++) {
            Transform pos = randomPosition(treeSet);
            this.levelBuilder.addTree(pos.getPosition(), "images/greenTree.png");
        }

        playerPosition = randomPosition(treeSet);

        return this.levelBuilder.create();
    }

    private Transform randomPosition(Set<GridPoint2> set){
        GridPoint2 pos;
        do {
            int x = this.random.nextInt(this.width);
            int y = this.random.nextInt(this.height);

            pos = new GridPoint2(x, y);
            set.add(pos);
        } while (!set.contains(pos));

        return new Transform(pos);
    }

    public Transform getPlayerPosition() {
        return playerPosition;
    }
}
