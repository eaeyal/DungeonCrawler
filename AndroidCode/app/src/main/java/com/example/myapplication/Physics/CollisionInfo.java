package com.example.myapplication.Physics;

public class CollisionInfo {
    private Tile tile;
    private int tilePosX;
    private int tilePosY;

    private int collisionPosX;
    private int collisionPosY;

    public CollisionInfo(Tile tile, int tilePosX, int tilePosY,
                         int collisionPosX, int collisionPosY) {
        this.setTile(tile);
        this.setTilePosX(tilePosX);
        this.setTilePosY(tilePosY);
        this.setCollisionPosX(collisionPosX);
        this.setCollisionPosY(collisionPosY);
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public int getTilePosX() {
        return tilePosX;
    }

    public void setTilePosX(int tilePosX) {
        this.tilePosX = tilePosX;
    }

    public int getTilePosY() {
        return tilePosY;
    }

    public void setTilePosY(int tilePosY) {
        this.tilePosY = tilePosY;
    }

    public int getCollisionPosX() {
        return collisionPosX;
    }

    public void setCollisionPosX(int collisionPosX) {
        this.collisionPosX = collisionPosX;
    }

    public int getCollisionPosY() {
        return collisionPosY;
    }

    public void setCollisionPosY(int collisionPosY) {
        this.collisionPosY = collisionPosY;
    }
}
