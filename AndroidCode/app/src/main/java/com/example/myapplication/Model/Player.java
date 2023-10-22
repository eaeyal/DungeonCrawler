package com.example.myapplication.Model;

import android.media.Image;
import android.view.KeyEvent;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.View.InitialGameScreen;
import com.example.myapplication.View.PlayerConfigActivity;
import com.example.myapplication.ViewModel.InitialGameScreenViewModel;
import com.example.myapplication.ViewModel.Subscriber;

import java.util.ArrayList;
import java.util.List;


public class Player {
    private String name;
    private int healthPoints; // 75 for easy, 65 for medium, 55 for hard
    private int image; // 1 for sprite_1, 2 for sprite_2, 3 for sprite_3

    private int score;

    private int x = 0;
    private int y = 0;

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    private Player() {
        this.name = null;
        this.healthPoints = -1;
        this.image = -1;

    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
        notifySubscribers();
    }

    public int setXCoordinate(int x) {
        this.x = x;
        notifySubscribers();
        return x;
    }

    public void setYCoordinate(int y) {
        notifySubscribers();
        this.y = y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getXCoordinate() {
        return x;
    }

    public int getYCoordinate() {
        return y;
    }

    public int getImage() {return image;}
    private static Player instance = null;

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /*

    public void moveRight() {

        //int sprinteId = Player.getInstance().getS


        int x = Player.getInstance().playerSprite.getRight();
        x+=0.25;
        Player.getInstance().playerSpriteA.setRight(x);


    }



    public void moveLeft() {
        int x = Player.getInstance().getXCoordinate();
        x+=-0.25;
        Player.getInstance().setCoordinates(x,Player.getInstance().getYCoordinate());
    }

    public void moveUp() {
        int y = Player.getInstance().getYCoordinate();
        y+=1;
        Player.getInstance().setCoordinates(Player.getInstance().getXCoordinate(), y);
    }

    public void moveDown() {
        int y = Player.getInstance().getYCoordinate();
        y=-1;
        Player.getInstance().setCoordinates(Player.getInstance().getXCoordinate(), y);
    }

    public void doNothing() {
        int x = Player.getInstance().getXCoordinate();
        int y = Player.instance.getYCoordinate();
    }





    public boolean playerMovement(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                moveLeft();
                return true;

            case KeyEvent.KEYCODE_DPAD_RIGHT:
                moveRight();
                return true;

            case KeyEvent.KEYCODE_DPAD_UP:
                moveUp();
                return true;

            case KeyEvent.KEYCODE_DPAD_DOWN:
                moveDown();
                return true;
            default:
                doNothing();
                return true;
        }
        //if (Player.getInstance().getXCoordinate() > initialGameS)
    }

     */
    private List<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    protected void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(this); //update playerSprite
        }
    }





}



