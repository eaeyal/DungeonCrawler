package com.example.myapplication.Model;

public class PowerupsPlayerJumpEnemy extends PowerUps{

    int X;
    int Y;
    boolean hasPowerUp;
    @Override
    void setX(int X) {
        this.X = X;
    }

    @Override
    void setY(int Y) {
        this.Y = Y;
    }

    @Override
    int getX() {
        return this.X;
    }

    @Override
    int getY() {
        return this.Y;
    }

    @Override
    void action() {
        
    }

    @Override
    void setPowerUps(boolean havePowerUps) {
        this.hasPowerUp = havePowerUps;
    }

    @Override
    boolean getPowerUps() {
        return this.hasPowerUp;
    }
}
