package com.example.myapplication.Model;

public class SuperSpeed extends PowerUps{

    private final int speed = 20;
    int X = 0;
    int Y = 0;
    boolean havePowerUp = false;
    public SuperSpeed(PowerUpInterface decoratedPlayer) {
        super(decoratedPlayer);
    }


    @Override
    public int powerUp() {
        Player.getInstance().setSpeed(Player.getInstance().getSpeed() +
                this.speed);
        havePowerUp = true;
        return Player.getInstance().getSpeed() + this.speed;
    }
}
