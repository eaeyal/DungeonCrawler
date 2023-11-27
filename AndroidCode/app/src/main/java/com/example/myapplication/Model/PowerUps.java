package com.example.myapplication.Model;

//player decorator
public abstract class PowerUps implements PowerUpInterface {
    boolean hasPowerUp;
    protected PowerUpInterface decoratedPlayer;
    public PowerUps(PowerUpInterface decoratedPlayer) {
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

    public int powerUp() {
        return 0;
    }

    public void setPowerUps(boolean havePowerUps) {
        this.hasPowerUp = havePowerUps;
    }

    public boolean getPowerUps() {
        return this.hasPowerUp;
    }
}
