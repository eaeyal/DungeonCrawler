package com.example.myapplication.Physics;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Tile {
    private int width;
    private int height;
    private ImageView sprite;
    private TileType type;

    public Tile(int width, int height, TileType type, ImageView sprite) {
        this.width = width;
        this.height = height;
        this.sprite = sprite;
        this.type = type;
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

    public TileType getType() {
        return type;
    }

    public static Tile fromSpriteId(int id, int width, int height, TileType type, Activity context) {
        RelativeLayout.LayoutParams defaultParams = new RelativeLayout.LayoutParams(width, height);
        ImageView tileFloorSprite = new ImageView(context);
        tileFloorSprite.setImageResource(id);
        tileFloorSprite.setLayoutParams(defaultParams);

        return new Tile(width, height, type, tileFloorSprite);
    }
}
