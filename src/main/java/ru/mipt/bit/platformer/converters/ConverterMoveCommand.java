package ru.mipt.bit.platformer.converters;

import com.badlogic.gdx.math.GridPoint2;
import org.awesome.ai.Recommendation;
import org.awesome.ai.state.movable.Orientation;
import ru.mipt.bit.platformer.collision.Collision;
import ru.mipt.bit.platformer.commands.command.MoveCommand;
import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.util.Direction;

public class ConverterMoveCommand {
    public static MoveCommand convert(Recommendation recommendation, Level level, Collision collision) {
        TankEntity tank = ConverterTankEntity.convert(recommendation.getActor());
        MoveCommand command = null;

        switch (recommendation.getActor().getOrientation()) {
            case S:
                command = new MoveCommand(Direction.Down, tank, level, collision);
                break;
            case E:
                command = new MoveCommand(Direction.Right, tank, level, collision);
                break;
            case N:
                command = new MoveCommand(Direction.Up, tank, level, collision);
                break;
            case W:
                command = new MoveCommand(Direction.Left, tank, level, collision);
                break;
        }

        return command;
    }
}
