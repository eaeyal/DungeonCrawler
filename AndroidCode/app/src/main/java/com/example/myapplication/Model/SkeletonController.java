package com.example.myapplication.Model;

public class SkeletonController extends EnemyController {
    @Override
    public Enemy createEnemy() {
        return new Skeleton();
    }


}



