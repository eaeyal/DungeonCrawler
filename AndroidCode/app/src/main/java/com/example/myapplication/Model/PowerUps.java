package com.example.myapplication.Model;

public abstract class PowerUps {
    abstract void setX(int X);
    abstract void setY(int Y);
    abstract int getX();
    abstract int getY();

    abstract void action();

    abstract void setPowerUps(boolean havePowerUps);

    abstract boolean getPowerUps();
}
