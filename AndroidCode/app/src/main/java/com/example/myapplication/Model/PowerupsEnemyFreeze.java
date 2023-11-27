package com.example.myapplication.Model;

public class PowerupsEnemyFreeze extends PowerUps {
    int X;
    int Y;
    boolean hasPowerUp;

    public PowerupsEnemyFreeze(PowerUpInterface decoratedPlayer) {
        super(decoratedPlayer);
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
