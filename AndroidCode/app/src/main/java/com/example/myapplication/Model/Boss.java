package com.example.myapplication.Model;

public class Boss implements Enemy {
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
        this.setX(600);
        this.setY(630);
    }

    // Enemy's attack pattern
    @Override
    public void attack() {
        // Attacks in a certain amount of tile.
        // needs to be implemented
    }

    @Override
    public void movement() {
        //300 should be replaced with the value that prevents it from going offscreen
        /* if (this.getX() < 50 ) {
            this.setX(this.getX() + 10);
            this.setY(this.getY() + 10);
            this.setSpeed(20);
        } else {
            this.setX(this.getX() - 10);
            this.setY(this.getY() - 10);
            this.setSpeed(10);
        } */

        if (getX() > 550) {
            reverseDirection = true;
        }
        if (getX() < 300) {
            reverseDirection = false;
        }
        if (reverseDirection) {
            setX(getX() - 100);
        } else {
            setX(getX() + 100);
        }

    }

    @Override
    public void noMovement() {
        speed = 0; //TODO check implementation
    }
}
