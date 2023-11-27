package com.example.myapplication.Model;

//player decorator
public abstract class PowerUps implements PlayerInterface {
    protected PlayerInterface decoratedPlayer;
    public PowerUps(PlayerInterface decoratedPlayer) {
        this.decoratedPlayer = decoratedPlayer;
    }
    public void setXCoordinate(int x) {
        decoratedPlayer.setXCoordinate(x);
    }
    public void setYCoordinate(int y) {
        decoratedPlayer.setYCoordinate(y);
    }
    public int getXCoordinate() {
        return decoratedPlayer.getXCoordinate();
    }
    public int getYCoordinate() {
        return decoratedPlayer.getYCoordinate();
    }

    public int getHealthPoints() {
        return decoratedPlayer.getHealthPoints();
    }

    abstract void setPowerUps(boolean havePowerUps);

    abstract boolean getPowerUps();
}
