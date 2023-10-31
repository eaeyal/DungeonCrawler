package com.example.myapplication.Model;

public class UndeadController extends EnemyController {
    public Enemy createEnemy() {
        return new Undead();
    }
}
