//package test.direction;
//
//import com.badlogic.gdx.math.GridPoint2;
//import org.junit.Assert;
//import org.junit.Test;
//import ru.mipt.bit.platformer.util.Transform;
//
//public class DirectionTest extends Assert {
//
//    @Test
//    public void StepInTheDirectionUpReturnValidTransform(){
//        Transform start = new Transform(new GridPoint2(0,1), 0f);
//        Direction up = Direction.Up;
//
//        Transform end = up.stepInTheDirection(start);
//
//        Transform excpect = new Transform(new GridPoint2(0, 2), 90f);
//
//        assertEquals(excpect.position, end.position);
//        assertEquals(excpect.rotation, end.rotation, 1e-2);
//    }
//
//    @Test
//    public void StepInTheDirectionDownReturnValidTransform(){
//        Transform start = new Transform(new GridPoint2(0,1), 0f);
//        Direction down = Direction.Down;
//
//        Transform end = down.stepInTheDirection(start);
//
//        Transform excpect = new Transform(new GridPoint2(0, 0), -90f);
//
//        assertEquals(excpect.position, end.position);
//        assertEquals(excpect.rotation, end.rotation, 1e-2);
//    }
//
//    @Test
//    public void StepInTheDirectionLeftReturnValidTransform(){
//        Transform start = new Transform(new GridPoint2(0,1), 0f);
//        Direction left = Direction.Left;
//
//        Transform end = left.stepInTheDirection(start);
//
//        Transform excpect = new Transform(new GridPoint2(-1, 1), 0f);
//
//        assertEquals(excpect.position, end.position);
//        assertEquals(excpect.rotation, end.rotation, 1e-2);
//    }
//
//    @Test
//    public void StepInTheDirectionRightReturnValidTransform(){
//        Transform start = new Transform(new GridPoint2(0,1), 0f);
//        Direction right = Direction.Right;
//
//        Transform end = right.stepInTheDirection(start);
//
//        Transform excpect = new Transform(new GridPoint2(1, 1), 0f);
//
//        assertEquals(excpect.position, end.position);
//        assertEquals(excpect.rotation, end.rotation, 1e-2);
//    }
//
//    @Test
//    public void StepInTheCustomDirectionReturnValidTransform(){
//        Transform start = new Transform(new GridPoint2(0,1), 0f);
//        Direction custom = new Direction(new GridPoint2(1, 1), 180f);
//
//        Transform end = custom.stepInTheDirection(start);
//
//        Transform excpect = new Transform(new GridPoint2(1, 2), 180f);
//
//        assertEquals(excpect.position, end.position);
//        assertEquals(excpect.rotation, end.rotation, 1e-2);
//    }
//}
