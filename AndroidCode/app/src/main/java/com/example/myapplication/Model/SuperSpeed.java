package com.example.myapplication.Model;

public class SuperSpeed extends PowerUps{

    public SuperSpeed(PowerUpInterface decoratedPlayer) {
        super(decoratedPlayer);
    }

    int X;
    int Y;
    boolean hasPowerUp = false;

    @Override
    public void setXCoordinate(int x) {
        this.X = X;
    }

    @Override
    public void setYCoordinate(int y) {
        this.Y = Y;
    }

    @Override
    public int getXCoordinate() {
        return this.X;
    }

    @Override
    public int getYCoordinate() {
        return this.Y;
    }

    public void action() {
        Player.getInstance().setSpeed(Player.getInstance().getSpeed() + 10);
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
