package com.example.myapplication.Physics;

public class CollisionInfo {
    public Tile tile;
    public int tilePosX;
    public int tilePosY;

    public int collisionPosX;
    public int collisionPosY;

    public CollisionInfo(Tile tile, int tilePosX, int tilePosY, int collisionPosX, int collisionPosY) {
        this.tile = tile;
        this.tilePosX = tilePosX;
        this.tilePosY = tilePosY;
        this.collisionPosX = collisionPosX;
        this.collisionPosY = collisionPosY;
    }
}
