package com.example.myapplication.ViewModel;

import com.example.myapplication.Model.Player;
import com.example.myapplication.RoomMapTile;

public class InitialGameScreenViewModel {
    private Player player;
    private Runnable updatedCallback;

    public InitialGameScreenViewModel() {
        this.player = Player.getInstance();
        this.player.setScore(100);
    }



    public Player getPlayer() {
        return player;
    }

    public void updateScore() {
        if (player.getScore() > 0) {
            player.setScore(player.getScore() - 1);
        }

        onUpdated();
    }

    public int getScore() {
        return player.getScore();
    }

    public void onUpdated() {
        if (updatedCallback != null) {
            updatedCallback.run();
        }
    }

    public void onUpdatedCallback(Runnable r) {
        updatedCallback = r;
    }
}
