package com.example.myapplication.Physics;

import com.example.myapplication.Model.Player;

public interface CollisionResolutionStrategy {
    public void resolveCollision(CollisionInfo collisionInfo);
}
