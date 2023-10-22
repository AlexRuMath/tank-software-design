//package ru.mipt.bit.platformer.converters;
//
//import org.awesome.ai.Recommendation;
//import ru.mipt.bit.platformer.collision.Collision;
//import ru.mipt.bit.platformer.commands.command.MoveCommand;
//import ru.mipt.bit.platformer.entity.TankEntity;
//import ru.mipt.bit.platformer.gameobjects.TankGameObject;
//import ru.mipt.bit.platformer.level.Level;
//import ru.mipt.bit.platformer.util.Transform;
//
//public class ConverterMoveCommand {
//    public static MoveCommand convert(Recommendation recommendation, Level level, Collision collision) {
//        TankGameObject tank = ConverterTankEntity.convert(recommendation.getActor());
//        MoveCommand command = null;
//
//        switch (recommendation.getActor().getOrientation()) {
//            case S:
//                command = new MoveCommand(Transform.Down, tank, level, collision);
//                break;
//            case E:
//                command = new MoveCommand(Transform.Right, tank, level, collision);
//                break;
//            case N:
//                command = new MoveCommand(Transform.Up, tank, level, collision);
//                break;
//            case W:
//                command = new MoveCommand(Transform.Left, tank, level, collision);
//                break;
//        }
//
//        return command;
//    }
//}
