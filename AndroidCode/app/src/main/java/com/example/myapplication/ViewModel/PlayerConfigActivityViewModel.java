package com.example.myapplication.ViewModel;

import com.example.myapplication.GameContext;
import com.example.myapplication.Model.Player;

import java.util.HashMap;

public class PlayerConfigActivityViewModel {
    private static HashMap<String, Integer> diffStr2Int = new HashMap<String, Integer>() {
        {
            put("Easy", 1);
            put("Normal", 2);
            put("Hard", 3);
        }
    };

    private static HashMap<String, Integer> sprite2Int = new HashMap<String, Integer>() {
        {
            put("Sprite 1", 1);
            put("Sprite 2", 2);
            put("Sprite 3", 3);
        }
    };

    protected static int any2int(String d, HashMap<String, Integer> b) {
        return b.get(d);
    }

    private Player player;
    // assuming the activity don't get recreated on re-render like flutter does
    // we can keep states here
    private Integer difficulty = null;
    private Integer playerSprite = null;
    private String playerName = null;

    public PlayerConfigActivityViewModel() {
        this.player = Player.getInstance();
    }

    public boolean revalidateInput() {
        return difficulty != null && playerSprite != null && playerName != null
                && !playerName.isEmpty() && !playerName.trim().isEmpty();
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void updateDifficulty(String difficulty) {
        this.difficulty = any2int(difficulty, diffStr2Int);
    }

    public void updatePlayerSprite(String playerSprite) {
        this.playerSprite = any2int(playerSprite, sprite2Int);
    }

    public void finalizePlayer() {
        player.setImage(playerSprite);
        player.setName(playerName);
        player.setHealthPoints(75 - (difficulty - 1) * 10);
        GameContext.getInstance().setDifficulty(difficulty);
    }
}
