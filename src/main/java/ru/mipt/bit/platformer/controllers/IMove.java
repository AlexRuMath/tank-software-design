package ru.mipt.bit.platformer.controllers;

import com.badlogic.gdx.Input;
import ru.mipt.bit.platformer.entity.PlayerEntity;
import ru.mipt.bit.platformer.level.ILevelObstacle;

public interface IMove {
    void move(PlayerEntity playerEntity, Input input, ILevelObstacle levelObstacle);
}
