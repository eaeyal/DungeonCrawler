package com.example.myapplication.Model;

import android.widget.ImageView;

import com.example.myapplication.R;

public class Player {
    private String name;
    private int healthPoints; // 75 for easy, 65 for medium, 55 for hard
    private int image; // 1 for sprite_1, 2 for sprite_2, 3 for sprite_3

    private ImageView playerSpriteRenderer;

    private int x = 0;
    private int y = 0;

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public Player(String name, int difficulty, int image, ImageView playerSprite) {
        this.name = name;
        this.healthPoints = 75 - (difficulty - 1) * 10;
        this.image = image;

        this.playerSpriteRenderer = playerSprite;

        this.playerSpriteRenderer.setImageResource(getSpriteAsset());
        this.setCoordinates(150, 150);
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;

        this.playerSpriteRenderer.setX(x);
        this.playerSpriteRenderer.setY(y);
    }

    public int getSpriteAsset() {
        switch (image) {
        case 1:
            return R.drawable.sprite_1;
        case 2:
            return R.drawable.sprite_2;
        case 3:
            return R.drawable.sprite_3;
        default:
            return R.drawable.sprite_1;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
