package com.example.myapplication.Model;

public class SlimeController extends EnemyController {
    public Enemy createEnemy() {
        return new Slime();
    }
}
