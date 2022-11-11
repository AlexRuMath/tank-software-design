package ru.mipt.bit.platformer.collision;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.interfaces.IMoveEntity;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.util.Transform;

import java.util.ArrayList;
import java.util.List;

public class Collision {
    private final List<CollisionRequest> requests;

    public Collision() {
        this.requests = new ArrayList<>();
    }

    public void addRequest(CollisionRequest request){
        this.requests.add(request);
    }

    public CollisionResponse sendRequests(IMoveEntity entity, GridPoint2 endPosition, Level level) {
        for (CollisionRequest request : requests) {
            CollisionResponse response = request.check(entity, endPosition, level);
            if (response.type != CollisionType.NoCollision) {
                return response;
            }
        }

        return new CollisionResponse(CollisionType.NoCollision, null, null);
    }
}
