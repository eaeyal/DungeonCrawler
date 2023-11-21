package com.example.myapplication.Model;

public class PowerupsEnemyFreeze extends PowerUps {

    int X;
    int Y;
    boolean hasPowerUp;
    @Override
    public void setX(int X) {
        this.X = X;
    }

    @Override
    public void setY(int Y) {
        this.Y = Y;
    }

    @Override
    public int getX() {
        return this.X;
    }

    @Override
    public int getY() {
        return this.Y;
    }

    @Override
    public void action() {
        //implementation
    }

    @Override
    public void setPowerUps(boolean havePowerUps) {
        this.hasPowerUp = havePowerUps;
    }

    @Override
    public boolean getPowerUps() {
        return hasPowerUp;
    }
}
