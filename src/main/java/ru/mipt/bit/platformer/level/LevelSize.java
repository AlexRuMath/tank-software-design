package ru.mipt.bit.platformer.level;

public class LevelSize {
    public final int maxX;
    public final int maxY;
    public final int minX;
    public final int minY;

    public LevelSize(int maxX, int maxY, int minX, int minY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.minX = minX;
        this.minY = minY;
    }
}
