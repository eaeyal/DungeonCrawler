package com.example.myapplication.Model;

public class SlimeController extends EnemyController {
    @Override
    public Enemy createEnemy() {
        return new Slime();
    }
}
