package ru.mipt.bit.platformer.directors;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.builders.ILevelObstacleBuilder;
import ru.mipt.bit.platformer.builders.LevelObstacleBuilderDefault;
import ru.mipt.bit.platformer.level.IFillLevel;
import ru.mipt.bit.platformer.level.ILevelObstacle;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomLevel implements IFillLevel {
    private ILevelObstacleBuilder obstacleBuilder;
    private Random random;
    private int countObstacle = 10;
    private int width = 0;
    private int height = 0;

    private GridPoint2 playerPosition;

    public RandomLevel(int x, int y, int countObstacle) {
        this.countObstacle = countObstacle;
        this.obstacleBuilder = new LevelObstacleBuilderDefault();
        this.random = new Random();
        this.width = x;
        this.height = y;
    }

    public RandomLevel(int x, int y) {
        this.obstacleBuilder = new LevelObstacleBuilderDefault();
        this.random = new Random();
        this.width = x;
        this.height = y;
    }

    public ILevelObstacle fill() {
        Set<GridPoint2> point2Set = new HashSet<>();

        for (int i = 0; i < this.countObstacle; i++) {
            GridPoint2 pos = randomPosition(point2Set);
            this.obstacleBuilder.addTree(pos);
        }

        playerPosition = randomPosition(point2Set);
        return this.obstacleBuilder.create();
    }

    private GridPoint2 randomPosition(Set<GridPoint2> set){
        GridPoint2 pos;
        do {
            int x = this.random.nextInt(this.width);
            int y = this.random.nextInt(this.height);

            pos = new GridPoint2(x, y);
            set.add(pos);
        } while (!set.contains(pos));

        return pos;
    }

    public GridPoint2 getPlayerPosition() {
        return playerPosition;
    }
}
