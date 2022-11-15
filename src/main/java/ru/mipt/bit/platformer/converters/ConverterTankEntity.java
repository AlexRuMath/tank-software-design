//package ru.mipt.bit.platformer.converters;
//
//import com.badlogic.gdx.math.GridPoint2;
//import org.awesome.ai.state.movable.Actor;
//import org.awesome.ai.state.movable.Orientation;
//import ru.mipt.bit.platformer.entity.TankEntity;
//import ru.mipt.bit.platformer.fabrics.DefaultObjectFabric;
//import ru.mipt.bit.platformer.fabrics.IObjectFabric;
//import ru.mipt.bit.platformer.gameobjects.TankGameObject;
//import ru.mipt.bit.platformer.util.Transform;
//
//public class ConverterTankEntity {
//
//    public static float getRotation(Orientation orientation){
//        float rotation = 0f;
//
//        switch (orientation){
//            case S:
//                rotation = -90f;
//                break;
//            case E:
//                rotation = 0f;
//                break;
//            case N:
//                rotation = 90f;
//                break;
//            case W:
//                rotation = 180;
//                break;
//        }
//
//        return rotation;
//    }
//
//    public static TankGameObject convert(Actor actor){
//        GridPoint2 pos = new GridPoint2(
//                actor.getX(),
//                actor.getY()
//        );
//
//        float rotation = getRotation(actor.getOrientation());
//        Transform transform = new Transform(pos, rotation);
//        TankEntity tank = new TankEntity(transform);
//
//        GridPoint2 dest = new GridPoint2(actor.getDestX(), actor.getDestY());
//        Transform destTransform = new Transform(dest, rotation);
//
//        tank.setDestinationTransform(transform);
//
//        IObjectFabric objectFabric = new DefaultObjectFabric();
//
//        return tank;
//    }
//}
