//package test.tank;
//
//import com.badlogic.gdx.math.GridPoint2;
//import org.junit.Assert;
//import org.junit.Test;
//import ru.mipt.bit.platformer.entity.Health;
//import ru.mipt.bit.platformer.entity.TankEntity;
//import ru.mipt.bit.platformer.entity.interfaces.IGun;
//import ru.mipt.bit.platformer.entity.interfaces.IHealth;
//import ru.mipt.bit.platformer.entity.interfaces.IMoveablePart;
//import ru.mipt.bit.platformer.entity.parts.DefaultMovePart;
//import ru.mipt.bit.platformer.entity.parts.DefaultTankGun;
//import ru.mipt.bit.platformer.util.Transform;
//
//public class TankEntityTest extends Assert {
//
//    @Test
//    public void TankSetMovementProgressValid(){
//        Transform tankTransform = new Transform(new GridPoint2(1, 1), 0f);
//        IHealth health = new Health(1);
//        IMoveablePart moveablePart = new DefaultMovePart(1f, 0f, null, tankTransform);
//        IGun gun = new DefaultTankGun(1f);
//
//        TankEntity tankEntity = new TankEntity(health, moveablePart, gun);
//
//        float expectedMovementProgress = 0.25f;
//        tankEntity.setMovementProgress(expectedMovementProgress);
//
//        assertEquals(tankEntity.getMovementProgress(), expectedMovementProgress, 1e-2);
//    }
//
//    @Test
//    public void TankSetDestinationTransform(){
//        Transform tankTransform = new Transform(new GridPoint2(1, 1), 0f);
//        TankEntity tankEntity = new TankEntity(tankTransform);
//
//        Transform excpectedDestination = new Transform(new GridPoint2(1, 2), 90f);
//
//        tankEntity.setDestinationTransform(excpectedDestination);
//
//        assertEquals(tankEntity.getDestinationTransform(), excpectedDestination);
//    }
//
//    @Test
//    public void TankSetDestinationTransformAsPosition(){
//        Transform tankTransform = new Transform(new GridPoint2(1, 1), 0f);
//        Transform destination = new Transform(new GridPoint2(1, 2), 90f);
//        TankEntity tankEntity = new TankEntity(tankTransform);
//
//        tankEntity.setDestinationTransform(destination);
//        tankEntity.setDestinationPositionAsPosition();
//
//        Transform position = tankEntity.getTransform();
//
//        assertEquals(position.position, destination.position);
//        assertEquals(position.rotation, destination.rotation, 1e-2);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void TankSetMovementProgressMoreThanOne(){
//        Transform tankTransform = new Transform(new GridPoint2(1, 1), 0f);
//        TankEntity tankEntity = new TankEntity(tankTransform);
//
//        float expectedMovementProgress = 1.25f;
//        tankEntity.setMovementProgress(expectedMovementProgress);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void TankSetMovementProgressLessThanZero(){
//        Transform tankTransform = new Transform(new GridPoint2(1, 1), 0f);
//        TankEntity tankEntity = new TankEntity(tankTransform);
//
//        float expectedMovementProgress = -1.25f;
//        tankEntity.setMovementProgress(expectedMovementProgress);
//    }
//}
