package com.example.myapplication.Model;

public class BasePowerUp implements PowerUpInterface{
    int x;
    int y;
    boolean hasPowerUp;
    int healthPoints;

    public void setXCoordinate(int x) {
        this.x = x;
    }
    public void setYCoordinate(int y) {
        this.y = y;
    }
    public int getXCoordinate() {
        return this.x;
    }
    public int getYCoordinate() {
        return this.y;
    }

    @Override
    public int powerUp() {
        return 0;
    }


    public void setPowerUps(boolean havePowerUps) {
        this.hasPowerUp = havePowerUps;
    }
    public boolean getPowerUps() {
        return hasPowerUp;
    }
}
