package com.example.myapplication.Model;

public class Slime implements Enemy {
    private int spriteId;
    private int x;
    private int y;
    private int healthPoints;
    private int attackDamage = 10;
    private int speed;
    private int direction;

    private boolean reverseDirection;

    public int getSpriteId() {
        return spriteId;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getHealthPoints() {
        return healthPoints;
    }
    public int getSpeed() {
        return speed;
    }
    public int getAttackDamage() {
        return attackDamage;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpriteId(int spriteId) {
        this.spriteId = spriteId;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    @Override
    public void startPos() {
        reverseDirection = false;
        this.setX(210);
        this.setY(634);
    }
    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Enemy's attack pattern
    @Override
    public void attack() {
        // Attacks in a certain amount of tile.
    }

    @Override
    public void movement() {
        //Moves the slime from left to right opposite of the other movements.
        if (getX() >= 210 && getX() <= 600 && getY() == 634) {
            if (getX() == 600) {
                reverseDirection = true;
            }
            if (getX() == 210) {
                reverseDirection = false;
            }
            if (reverseDirection) {
                setX(getX() - 10);
            } else {
                setX(getX() + 10);
            }
        }
    }

    @Override
    public void noMovement() {
        speed = 0; //TODO
    }

}
