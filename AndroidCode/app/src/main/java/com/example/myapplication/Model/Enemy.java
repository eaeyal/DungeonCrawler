package com.example.myapplication.Model;

public interface Enemy {

    //getters for enemy interface
    public int getSpriteId();
    public int getX();
    public int getY();
    public int getHealthPoints();
    public int getSpeed();
    public int getAttackDamage();
    //setters for enemy interface
    public void setX(int x);

    public void setY(int y);

    public void setSpriteId(int spriteId);

    public void setHealthPoints(int healthPoints);

    public void setSpeed(int speed);

    public void setAttackDamage(int attackDamage);
    /*
    Unsure how sprite is displayed, but this method will display the sprite on screen.
     */
    void startPos();

    // Enemy's attack pattern
    void attack();

    //methods depending on whether or not enemy moves
    void movement();

    void noMovement();
}
