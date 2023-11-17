package com.example.myapplication.Model;

public class ExtraHealthPoints extends  PowerUps{
    int X = 0;
    int Y = 0;
    boolean havePowerUp = false;
    public ExtraHealthPoints(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    @Override
    public void setPosition(int X, int Y) {
        this.X = 50;
        this.Y = 50;
    }

    @Override
    public int getX() {
        return X;
    }

    @Override
    public int getY() {
        return Y;
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
    public void action() {
        Player.getInstance().setHealthPoints(Player.getInstance().getHealthPoints() + 10); //TODO change 10 if necessary
    }
}
