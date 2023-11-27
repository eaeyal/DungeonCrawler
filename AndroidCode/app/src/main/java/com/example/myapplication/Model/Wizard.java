package com.example.myapplication.Model;

public class Wizard implements Enemy {
    private int spriteId;
    private int x;
    private int y;
    private int healthPoints;
    private int attackDamage;
    private int speed;
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

    public void setCoordinates(int x, int y) {
        reverseDirection = false;
        this.x = x;
        this.y = y;
    }
    @Override
    public void startPos() {
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
        // move top to bottom
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
        if (getY() > 2235) {
            reverseDirection = true;
        }
        if (getY() < 630) {
            reverseDirection = false;
        }
        if (reverseDirection) {
            setY(getY() - 45);
        } else {
            setY(getY() + 45);
        }
    }

    @Override
    public void noMovement() {
        speed = 0; //ToDo
    }
}
