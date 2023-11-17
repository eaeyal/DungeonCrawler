package com.example.myapplication.Model;

public abstract class PowerUps {
    abstract void setPosition(int X, int Y);
    abstract int getX();
    abstract int getY();

    abstract void action();

    abstract void setPowerUps(boolean havePowerUps);

    abstract boolean getPowerUps();
}
