package com.example.myapplication.Physics;

import com.example.myapplication.Model.Player;

public class CollisionResolutionIgnore implements CollisionResolutionStrategy {
    @Override
    public void resolveCollision(CollisionInfo collisionInfo) {
        // Do nothing
        return;
    }
}
