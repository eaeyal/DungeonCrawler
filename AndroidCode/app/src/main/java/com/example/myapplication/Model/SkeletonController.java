package com.example.myapplication.Model;

public class SkeletonController extends EnemyController {
    @Override
    public Enemy createEnemy() {
        return new Skeleton();
    }

    /*public void movement() {
        Skeleton.getInstance().setCoordinates(Skeleton.getInstance().getX() + 10, Skeleton.getInstance().getX() + 10);
    }

     */
}
