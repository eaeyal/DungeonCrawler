package com.example.myapplication.Physics;

import android.util.Log;

public class CollisionResolutionRevert implements CollisionResolutionStrategy {
    public void resolveCollision(CollisionInfo collisionInfo) {
        Log.d("CollisionResolutionRevert",
                "Collision resolution revert. CollisionInfo: " + collisionInfo.toString());
    }
}
