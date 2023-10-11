package com.example.myapplication.ViewModel;

import com.example.myapplication.Model.Player;

public class InitialGameScreenViewModel {
    private Player player;
    private int score = 100;

    private Runnable updatedCallback;

    public InitialGameScreenViewModel() {
        this.player = Player.getInstance();
    }

    public Player getPlayer() {
        return player;
    }

    public void updateScore() {
        if (score > 0) {
            score -= 5;
        }
        onUpdated();
    }

    public int getScore() {
        return score;
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
