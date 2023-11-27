package com.example.myapplication.Model;

public class Sword {
    private int x;

    private int y;
    private int slashX;
    private int slashY;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void attack() {
        slashX = this.x + 85;
        slashY = this.y;
    }
}
