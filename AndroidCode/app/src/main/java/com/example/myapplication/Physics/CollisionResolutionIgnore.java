package com.example.myapplication.Physics;

public class CollisionResolutionIgnore implements CollisionResolutionStrategy {
    @Override
    public void resolveCollision(CollisionInfo collisionInfo) {
        // Do nothing
        return;
    }
}
