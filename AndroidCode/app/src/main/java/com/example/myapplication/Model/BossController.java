package com.example.myapplication.Model;

public class BossController extends EnemyController {
    @Override
    public Enemy createEnemy() {
        return new Boss();
    }
}
