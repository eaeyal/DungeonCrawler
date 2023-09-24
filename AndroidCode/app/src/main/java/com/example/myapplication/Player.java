package com.example.myapplication;

import android.widget.ImageView;

public class Player {
    String name;
    int difficulty; // 1 for easy, 2 for medium, 3 for hard
    int healthPoints; // 75 for easy, 65 for medium, 55 for hard
    int image; // 1 for sprite_1, 2 for sprite_2, 3 for sprite_3

    ImageView playerSpriteRenderer;

    int x = 0;
    int y = 0;

    public Player(String name, int difficulty, int image, ImageView playerSprite) {
        this.name = name;
        this.difficulty = difficulty;
        this.healthPoints = 75 - (difficulty - 1) * 10;
        this.image = image;

        this.playerSpriteRenderer = playerSprite;

        this.playerSpriteRenderer.setImageResource(getSpriteAsset());
        this.setCoordinates(x, y);
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
}
