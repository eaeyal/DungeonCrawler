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
