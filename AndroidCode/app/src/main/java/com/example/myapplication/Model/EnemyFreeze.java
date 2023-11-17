package com.example.myapplication.Model;

public class EnemyFreeze extends PowerUps{

    int X;
    int Y;

    boolean freeze = false;

    boolean havePowerUps = false;
    public EnemyFreeze(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }
    @Override
    void setPosition(int X, int Y) {
        this.X = 50; //TODO change values
        this.Y = 50;
    }

    @Override
    int getX() {
        return X;
    }

    @Override
    int getY() {
        return Y;
    }

    @Override
    void action() {
        freeze = true;
        //TODO call enemy and set speed =0

    }

    @Override
    void setPowerUps(boolean havePowerUps) {
        havePowerUps = true;
    }

    @Override
    boolean getPowerUps() {
        return havePowerUps;
    }
}
