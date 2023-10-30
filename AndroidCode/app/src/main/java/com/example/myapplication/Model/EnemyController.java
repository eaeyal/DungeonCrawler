package com.example.myapplication.Model;

// Creator class for enemy
public abstract class EnemyController {
    public Enemy enemy;

    // Calls this to create an enemy.
    public abstract Enemy createEnemy();


    // Creates the enemy using the correct createEnemy() method in one of the EnemyController inheritors.
    public void render() {
        enemy = createEnemy();
        enemy.startPos();
    }

    public void movement() {
        enemy.movement();
    }


}
