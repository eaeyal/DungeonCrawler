package com.example.myapplication.Physics;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class RoomMapTile {
    private final int tileWidth = 128;
    private final int tileHeight = 128;

    private int tileFloorSpriteId;
    private int tileWallSpriteId;
    private int tileExitSpriteId;

    private Activity invokeContext;

    private int xOffset;
    private int yOffset;

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

    public void configTileExitSpriteId(int spriteId) {
        tileExitSpriteId = spriteId;
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
        initPrimitiveTileLayout();
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
                tiles[i][j] = Tile.fromSpriteId(tileFloorSpriteId,
                        tileWidth, tileHeight, TileType.Floor,
                        invokeContext);
            }
        }

        // Set the wall tiles, top and bottom, left and right
        // Top and bottom
        for (int i = 0; i < width; i++) {
            tiles[i][0] = Tile.fromSpriteId(tileWallSpriteId, tileWidth, tileHeight, TileType.Wall,
                    invokeContext);

            tiles[i][height - 1] = Tile.fromSpriteId(tileWallSpriteId,
                    tileWidth, tileHeight, TileType.Wall,
                    invokeContext);
        }

        // Left and right
        for (int i = 0; i < height; i++) {
            tiles[0][i] = Tile.fromSpriteId(tileWallSpriteId, tileWidth, tileHeight, TileType.Wall,
                    invokeContext);

            tiles[width - 1][i] = Tile.fromSpriteId(tileWallSpriteId,
                    tileWidth, tileHeight, TileType.Wall,
                    invokeContext);
        }

        replaceRandomWallTileAsExitTile();
    }

    public CollisionInfo getTileAtPoint(int x, int y) {
        int totalWidth = width * tiles[0][0].getWidth();
        int totalHeight = height * tiles[0][0].getHeight();

        int left = xOffset - (totalWidth / 2);
        int top = yOffset - (totalHeight / 2);

        for (int i = 0; i < tiles.length; i++) {
            int xTile = left + i * tiles[0][0].getWidth();
            for (int j = 0; j < tiles[i].length; j++) {
                int yTile = top + j * tiles[0][0].getHeight();

                if (x >= xTile && x < xTile + tiles[0][0].getWidth()
                        && y >= yTile && y < yTile + tiles[0][0].getHeight()) {
                    return new CollisionInfo(tiles[i][j], xTile, yTile, x, y);
                }
            }
        }
        return null; // Return null if no tile is found at the given point
        // }
    }

    public List<CollisionInfo> getCollidingTiles(int x, int y, int width, int height) {
        // x, y is the top left corner of the rectangle
        // need to find all other corners of the rectangle
        // and then find the tiles that intersect with the rectangle
        // and return them

        List<CollisionInfo> intersectingTiles = new ArrayList<>();

        CollisionInfo topLeft = getTileAtPoint(x, y);
        CollisionInfo topRight = getTileAtPoint(x + width, y);
        CollisionInfo bottomLeft = getTileAtPoint(x, y + height);
        CollisionInfo bottomRight = getTileAtPoint(x + width, y + height);

        if (topLeft != null) {
            intersectingTiles.add(topLeft);
        }

        if (topRight != null) {
            intersectingTiles.add(topRight);
        }

        if (bottomLeft != null) {
            intersectingTiles.add(bottomLeft);
        }

        if (bottomRight != null) {
            intersectingTiles.add(bottomRight);
        }

        return intersectingTiles;
    }

    public void setXYOffset(int x, int y) {
        xOffset = x;
        yOffset = y;
    }

    public void drawTileLayout(RelativeLayout invokeContext) {
        drawTileLayout(invokeContext, xOffset, yOffset);
    }

    private void drawTileLayout(RelativeLayout invokeContext, int centerX, int centerY) {
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

    public static RoomMapTile fromTileStyle(int tileFloorSpriteId,
                                            int tileWallSpriteId, int tileExitSpriteID,
                                            int xOffset, int yOffset) {
        RoomMapTile roomMapTile = new RoomMapTile();
        roomMapTile.configTileFloorSpriteId(tileFloorSpriteId);
        roomMapTile.configTileWallSpriteId(tileWallSpriteId);
        roomMapTile.configTileExitSpriteId(tileExitSpriteID);
        roomMapTile.setXYOffset(xOffset, yOffset);

        return roomMapTile;
    }

    public RoomMapTile build(int width, int height,
                             Activity invokeContext) {
        this.configInvokeContext(invokeContext);
        this.initPrimitiveTileLayout();
        this.updateTileDimensionsAndRecomputeLayout(width, height,
                (RelativeLayout) invokeContext.findViewById(R.id.gameLayout));
        return this;
    }

    public void replaceRandomWallTileAsExitTile() {
        for (int i = 1; i < tiles.length - 1; i++) {
            for (int j = 0; j < tiles[i].length - 1; j++) {
                if (tiles[i][j].getType() == TileType.Wall) {
                    tiles[i][j] = Tile.fromSpriteId(tileExitSpriteId, tileWidth, tileHeight,
                            TileType.Exit, invokeContext);
                    return;
                }
            }
        }
    }
}
