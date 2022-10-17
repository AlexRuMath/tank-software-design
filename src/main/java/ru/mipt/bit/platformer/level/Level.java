package ru.mipt.bit.platformer.level;

import ru.mipt.bit.platformer.gameobjects.TankGameObject;
import ru.mipt.bit.platformer.level.DTO.ILevelObstacle;
import ru.mipt.bit.platformer.level.DTO.ITanks;

public class Level {
    public ILevelObstacle levelObstacle;
    public ITanks levelTanks;
    public TankGameObject playerTank;

    public int height;
    public int width;

    public Level(ILevelObstacle levelObstacle, ITanks levelTanks, TankGameObject playerTank, int height, int width){
        this.levelObstacle = levelObstacle;
        this.levelTanks = levelTanks;
        this.playerTank = playerTank;
        this.height = height;
        this.width = width;
    }
}
