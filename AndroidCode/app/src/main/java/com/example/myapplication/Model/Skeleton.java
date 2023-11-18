package com.example.myapplication.Model;

public class Skeleton implements Enemy {
    private int spriteId; // Set skeleton sprite
    private int x;
    private int y;
    private int healthPoints;
    private int attackDamage;
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

    public void setCoordinates(int x, int y) {
        this.x = x;
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
        this.setX(600);
        this.setY(950);
    }

    // Enemy's attack pattern
    @Override
    public void attack() {
        // Attacks in a certain amount of tile.
    }


    @Override
    public void movement() {
        /*
        bot left:
        210, 2240

        bot right:
        1170, 2240

        top right
        1170, 634

        top left
        210, 634
         */

        // move left to right
        if (getX() >= 210 && getX() <= 1170 && getY() == 950) {
            if (getX() == 1170) {
                reverseDirection = true;
            }
            if (getX() == 210) {
                reverseDirection = false;
            }
            if (reverseDirection) {
                setX(getX() - 30);
            } else {
                setX(getX() + 30);
            }
        }
    }
}
