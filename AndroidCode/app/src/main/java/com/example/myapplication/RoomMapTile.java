package com.example.myapplication;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class RoomMapTile {
    private final int tileWidth = 128;
    private final int tileHeight = 128;

    private int tileFloorSpriteId;
    private int tileWallSpriteId;

    private Activity invokeContext;

    private Tile[][] tiles;

    private int width;
    private int height;

    public RoomMapTile() {
        this.width = 5;
        this.height = 5;

        this.tiles = new Tile[width][height];
    }

    public void configTileFloorSpriteId(int spriteId) {
        tileFloorSpriteId = spriteId;
    }

    public void configTileWallSpriteId(int spriteId) {
        tileWallSpriteId = spriteId;
    }

    public void configInvokeContext(Activity context) {
        invokeContext = context;
    }

    private void undrawAndDisposePreexistingLayout(RelativeLayout invokeContext) {
        if (this.tiles == null) {
            return;
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                invokeContext.removeView(tiles[i][j].getSprite());
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j].getSprite().setImageDrawable(null); // Clear the image resource
                tiles[i][j] = null; // Set the Tile reference to null
            }
        }
        tiles = null; // Set the tiles array itself to null
    }

    public void updateTileDimensionsAndRecomputeLayout(int width, int height,
                                                       RelativeLayout invokeContext) {
        undrawAndDisposePreexistingLayout(invokeContext);

        this.width = width;
        this.height = height;

        this.tiles = new Tile[width][height];
    }

    public void updateTileDimensionsTileStyleAndRecomputeLayout(int width, int height,
                                                                int tileFloorSpriteId,
                                                                int tileWallSpriteId,
                                                                RelativeLayout invokeContext) {
        undrawAndDisposePreexistingLayout(invokeContext);

        this.width = width;
        this.height = height;

        this.tiles = new Tile[width][height];

        this.tileFloorSpriteId = tileFloorSpriteId;
        this.tileWallSpriteId = tileWallSpriteId;
    }

    public void initPrimitiveTileLayout() {
        // Initialize the tiles array with the floor tiles
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // create a new tileFloor everytime
                tiles[i][j] = Tile.fromSpriteId(tileFloorSpriteId, tileWidth, tileHeight,
                        invokeContext);
            }
        }

        // Set the wall tiles, top and bottom, left and right
        // Top and bottom
        for (int i = 0; i < width; i++) {
            tiles[i][0] = Tile.fromSpriteId(tileWallSpriteId, tileWidth, tileHeight,
                    invokeContext);

            tiles[i][height - 1] = Tile.fromSpriteId(tileWallSpriteId, tileWidth, tileHeight,
                    invokeContext);
        }

        // Left and right
        for (int i = 0; i < height; i++) {
            tiles[0][i] = Tile.fromSpriteId(tileWallSpriteId, tileWidth, tileHeight,
                    invokeContext);

            tiles[width - 1][i] = Tile.fromSpriteId(tileWallSpriteId, tileWidth, tileHeight,
                    invokeContext);
        }
    }

    public void drawTileLayout(RelativeLayout invokeContext, int centerX, int centerY) {
        // centerX and centerY are the coordinates of the screen center
        // we need to render the tiles in a way that the player is always in the center of
        // the screen so we need to offset the tiles by the player's coordinates

        int totalWidth = width * tileWidth;
        int totalHeight = height * tileHeight;

        int left = centerX - (totalWidth / 2);
        int top = centerY - (totalHeight / 2);

        int x;
        int y;
        for (int i = 0; i < width; i++) {
            x = left + i * tileWidth;
            for (int j = 0; j < height; j++) {
                y = top + j * tileHeight;
                tiles[i][j].getSprite().setX(x);
                tiles[i][j].getSprite().setY(y);
            }
        }

        // second pass, add each sprite to the invokeContext
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ImageView tileSprite = tiles[i][j].getSprite();
                if (tileSprite.getParent() != null) {
                    ((ViewGroup) tileSprite.getParent()).removeView(tileSprite);
                }
                invokeContext.addView(tileSprite, tileSprite.getLayoutParams());
            }
        }
    }
}
