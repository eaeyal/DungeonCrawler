package com.example.myapplication.Physics;

import android.util.Log;

import com.example.myapplication.Model.Player;

public class CollisionResolutionRevert implements CollisionResolutionStrategy {
    public void resolveCollision(CollisionInfo collisionInfo) {
        Log.d("CollisionResolutionRevert", "Collision resolution revert. CollisionInfo: " + collisionInfo.toString());
    }
}
