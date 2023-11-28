package com.example.myapplication.Model;

public class ExtraHealthPoints extends PowerUps {
    private final int heathPoints = 50;
    int X = 0;
    int Y = 0;
    boolean havePowerUp = false;

    public ExtraHealthPoints(PowerUpInterface decoratedPlayer) {
        super(decoratedPlayer);
    }


    @Override
    public int powerUp() {
        Player.getInstance().setSpeed(200);
        Player.getInstance().setHealthPoints(Player.getInstance().getHealthPoints() +
                this.heathPoints);
        havePowerUp = true;
        return Player.getInstance().getHealthPoints() + this.heathPoints;
    }
}
