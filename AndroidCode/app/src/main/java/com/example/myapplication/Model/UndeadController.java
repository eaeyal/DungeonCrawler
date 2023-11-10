package com.example.myapplication.Model;

public class UndeadController extends EnemyController {
    @Override
    public Enemy createEnemy() {
        return new Undead();
    }
}
