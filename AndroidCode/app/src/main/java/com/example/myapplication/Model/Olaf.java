package com.example.myapplication.Model;
public class Olaf implements Enemy {
    private int spriteId;
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
        this.setX(310);
        this.setY(734);
    }

    // Enemy's attack pattern
    @Override
    public void attack() {
        // Attacks in a certain amount of tile.
    }

    @Override
    public void movement() {
        // move in zig zag pattern
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

        // change direction if positively out of bounds
//        if (this.getX() > 1170) {
//            reverseDirection = true;
//        }
//        if (this.getX() < 210) {
//            reverseDirection = false;
//        }
//
//        if (this.getY() > 2240) {
//            reverseDirection = true;
//        }
//
//        if (this.getY() < 634) {
//            reverseDirection = false;
//        }
//
//        if (reverseDirection) {
//            setX(getX() - 10);
//            setY(getY() - 10);
//        } else {
//            setX(getX() + 10);
//            setY(getY() + 10);
//        }
    }
}

