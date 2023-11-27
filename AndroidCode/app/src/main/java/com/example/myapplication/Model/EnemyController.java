package com.example.myapplication.Model;

// Creator class for enemy
public abstract class EnemyController {
    private Enemy enemy;

    // Calls this to create an enemy.
    public abstract Enemy createEnemy();


    // Creates the enemy using the correct createEnemy()
    // method in one of the EnemyController inheritors.
    public void render() {
        setEnemy(createEnemy());
        getEnemy().startPos();
    }

    public void movement() {
        getEnemy().movement();
    }
    public void noMovement() {getEnemy().noMovement();}


    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
}
