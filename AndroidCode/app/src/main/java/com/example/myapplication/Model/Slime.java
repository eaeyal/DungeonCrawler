package com.example.myapplication.Model;

public class Slime implements Enemy {
    private int spriteId;
    private int x;
    private int y;
    private int healthPoints;
    private int attackDamage;
    private int speed;
    private int direction;

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
        this.setX(600);
        this.setY(950);
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
        // Change this for the movement
        /* if (Player.getInstance().getHealthPoints() < 65) {
            this.setSpeed(this.getSpeed() + 30);
            this.setCoordinates(this.getX() - 5, this.getY() + 2);
            this.setCoordinates(this.getX() + 5, this.getY() + 3);
        } else {
            this.setSpeed(20);
            this.setCoordinates(this.getX() + 5, this.getY() + 5);
            this.setSpeed(25);
            this.setCoordinates(this.getX() + 6, this.getY() + 6);
        } */
        switch (direction) {
            case 0:
                // move up
                this.setY(this.getY() - 21);
                direction++;
                break;
            case 1:
                // move down
                this.setY(this.getY() + 21);
                direction++;
                break;
            case 2:
                // move left
                this.setX(this.getX() - 21);
                direction++;
                break;
            case 3:
                this.setX(this.getX() + 21);
                direction = 0;
                break;
            default:
                break;
        }


    }

}
