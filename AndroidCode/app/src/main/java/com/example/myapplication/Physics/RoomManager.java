package com.example.myapplication.Physics;

import android.app.Activity;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class RoomManager {
    private List<RoomMapTile> rooms;

    private int currentRoomIndex;

    public RoomManager() {
        rooms = new ArrayList<>();
    }

    public void addRoom(RoomMapTile room) {
        rooms.add(room);
    }

    public void changeRoom(int index) {
        currentRoomIndex = index;
    }

    public RoomMapTile getCurrentRoom() {
        return rooms.get(currentRoomIndex);
    }

    public void drawRoom(RelativeLayout context) {
        RoomMapTile room = getCurrentRoom();
        room.drawTileLayout(context);
    }

    public RoomMapTile getRoom(int index) {
        return rooms.get(index);
    }

    public int getTotalRoomCount() {
        return rooms.size();
    }

    public int getCurrentRoomIndex() {
        return currentRoomIndex;
    }
}
