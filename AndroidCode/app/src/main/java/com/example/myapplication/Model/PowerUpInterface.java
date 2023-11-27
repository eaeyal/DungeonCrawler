package com.example.myapplication.Model;
public interface PowerUpInterface {
    public void setXCoordinate(int x);
    public void setYCoordinate(int y);
    public int getXCoordinate();
    public int getYCoordinate();

    public int powerUp();

    public void setPowerUps(boolean havePowerUp);
    public boolean getPowerUps();
}
