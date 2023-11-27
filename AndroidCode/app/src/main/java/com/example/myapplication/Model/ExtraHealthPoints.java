package com.example.myapplication.Model;

public class ExtraHealthPoints extends PowerUps {
    private final int heathPoints = 50;
    int X = 0;
    int Y = 0;
    boolean havePowerUp = false;

    public ExtraHealthPoints(PlayerInterface decoratedPlayer) {
        super(decoratedPlayer);
    }

    @Override
    public void setPowerUps(boolean havePowerUp) {
        this.havePowerUp = havePowerUp;
    }

    @Override
    public boolean getPowerUps() {
        return havePowerUp;
    }
    @Override
    public int getHealthPoints() {
        return super.getHealthPoints() + heathPoints;
    }
}
