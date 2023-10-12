package com.example.myapplication;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Tile {
    private int width;
    private int height;
    private ImageView sprite;

    public Tile(int width, int height, ImageView sprite) {
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static Tile FromSpriteId(int id, int width, int height, Activity context) {
        RelativeLayout.LayoutParams defaultParams = new RelativeLayout.LayoutParams(width, height);
        ImageView tileFloorSprite = new ImageView(context);
        tileFloorSprite.setImageResource(id);
        tileFloorSprite.setLayoutParams(defaultParams);

        return new Tile(width, height, tileFloorSprite);
    }
}
