package com.example.myapplication.Model;

public class BossController extends EnemyController{
    public Enemy createEnemy() {
        return new Boss();
    }
}
