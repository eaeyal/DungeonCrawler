package com.example.myapplication.Model;

public class SuperSpeed extends PowerUps{

    public SuperSpeed(PowerUpInterface decoratedPlayer) {
        super(decoratedPlayer);
    }

    int X;
    int Y;
    boolean hasPowerUp = false;

    @Override
    public void setPowerUps(boolean havePowerUps) {
        this.hasPowerUp = havePowerUps;
    }

    @Override
    public boolean getPowerUps() {
        return hasPowerUp;
    }
}
