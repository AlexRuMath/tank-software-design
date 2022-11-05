package ru.mipt.bit.platformer.controllers;

import com.badlogic.gdx.Input;
import ru.mipt.bit.platformer.util.Direction;

public interface IMoveController {
    Direction getDirection(Input input);
}
