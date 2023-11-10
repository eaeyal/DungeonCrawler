package com.example.myapplication.Model;

public class OlafController extends EnemyController {
    @Override
    public Enemy createEnemy() {
        return new Olaf();
    }
}
