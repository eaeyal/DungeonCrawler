package com.example.myapplication.Model;

import com.example.myapplication.Physics.CollisionInfo;
import com.example.myapplication.Physics.CollisionResolutionStrategy;
import com.example.myapplication.Physics.TileType;
import com.example.myapplication.ViewModel.Subscriber;

import java.util.ArrayList;
import java.util.List;


public class Player implements CollisionResolutionStrategy {
    private String name;
    private ArrayList<String> roomsVisited;
    private boolean winner;
    private int healthPoints; // 75 for easy, 65 for medium, 55 for hard
    private int image; // 1 for sprite_1, 2 for sprite_2, 3 for sprite_3
    private int score;
    private int x = 0;
    private int y = 0;
    private int previousX = 0;
    private int previousY = 0;
    private int spriteId;
    private int speed = 10;
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
    private static Player instance = null;

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public int getImage() {
        return image;
    }

    public void setCoordinatesNoNotify(int x, int y) {

    }


    public void updateCoordinatesWithoutNotification(int x, int y) {

        this.x = x;
        this.y = y;
    }
    public void setCoordinates(int x, int y) {
        savePrevCoord();
        this.x = x;
        this.y = y;
        notifySubscribers();
    }
    public void revertCoordinates() {
        this.setCoordinates(this.previousX, this.previousY);
    }

    private void savePrevCoord() {
        this.previousX = this.x;
        this.previousY = this.y;
    }

    public void setXCoordinate(int x) {
        savePrevCoord();
        this.x = x;
        notifySubscribers();
    }
    public void setYCoordinate(int y) {
        savePrevCoord();
        this.y = y;
        notifySubscribers();
    }
    public void addRoom(String... rooms) {
        if (roomsVisited == null) {
            roomsVisited = new ArrayList<String>();
        }
        for (String room : rooms) {
            roomsVisited.add(room);
        }
    }
    public ArrayList<String> getRoomList() {
        if (roomsVisited == null) {
            roomsVisited = new ArrayList<String>();
        }
        return roomsVisited;
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



    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getSpeed() {
        return this.speed;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean getOutcome() {
        return winner;
    }

    public void setWinner() {
        winner = true;
    }

    public void setLoser() {
        winner = false;
    }

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

    @Override
    public void resolveCollision(CollisionInfo collision) {
        if (collision.getTile().getType() == TileType.Floor) {
            return;
        }

        boolean movingLeft = this.previousX > this.x;
        boolean movingRight = this.previousX < this.x;
        boolean movingUp = this.previousY > this.y;
        boolean movingDown = this.previousY < this.y;

        int tileLeft = collision.getTilePosX();
        int tileRight = tileLeft + collision.getTile().getWidth();
        int tileTop = collision.getTilePosY();
        int tileBottom = tileTop + collision.getTile().getHeight();

        int overLapX;
        if (movingLeft) {
            overLapX = collision.getCollisionPosX() - tileRight;
        } else {
            overLapX = collision.getCollisionPosX() - tileLeft;
        }

        int overLapY;
        if (movingUp) {
            overLapY = collision.getCollisionPosY() - tileBottom;
        } else {
            overLapY = collision.getCollisionPosY() - tileTop;
        }

        overLapY = Math.abs(overLapY);
        overLapX = Math.abs(overLapX);

        // if we were moving horizontally and we are overlapping on the left side
        // then we need to move the player to the right using the overlap amount
        if (movingLeft && overLapX > 0) {
            this.setX(this.getX() + overLapX);
        }

        // if we were moving horizontally and we are overlapping on the right side
        // then we need to move the player to the left using the overlap amount
        if (movingRight && overLapX > 0) {
            this.setX(this.getX() - overLapX);
        }

        // if we were moving vertically and we are overlapping on the top side
        // then we need to move the player down using the overlap amount
        if (movingUp && overLapY > 0) {
            this.setY(this.getY() + overLapY);
        }

        // if we were moving vertically and we are overlapping on the bottom side
        // then we need to move the player up using the overlap amount
        if (movingDown && overLapY > 0) {
            this.setY(this.getY() - overLapY);
        }
    }
}
