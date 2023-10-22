package com.example.myapplication;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import android.view.KeyEvent;

import com.example.myapplication.Model.Player;
import com.example.myapplication.Model.Score;
import com.example.myapplication.View.InitialGameScreen;
import com.example.myapplication.ViewModel.PlayerConfigActivityViewModel;
import com.example.myapplication.ViewModel.InitialGameScreenViewModel;


import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;

public class PlayerTests {

    PlayerConfigActivityViewModel playerViewModel = new PlayerConfigActivityViewModel();
    Player player;
    Score score;
    InitialGameScreenViewModel gameViewModel;


    @Test
    public void testPlayerName() {
        player = Player.getInstance();
        playerViewModel.setPlayerName("Bob");
        playerViewModel.updatePlayerSprite("Sprite 1");
        playerViewModel.updateDifficulty("Easy");
        playerViewModel.finalizePlayer();
        assertEquals("Bob", player.getName());
    }

    @Test
    public void testPlayerEasyDifficulty() {
        player = Player.getInstance();
        playerViewModel.setPlayerName("Bob");
        playerViewModel.updatePlayerSprite("Sprite 1");
        playerViewModel.updateDifficulty("Easy");
        playerViewModel.finalizePlayer();
        // Player starts with 75 on easy mode
        assertEquals(75, player.getHealthPoints());
    }

    @Test
    public void testPlayerNormalDifficulty() {
        player = Player.getInstance();
        playerViewModel.setPlayerName("Bob");
        playerViewModel.updatePlayerSprite("Sprite 1");
        playerViewModel.updateDifficulty("Normal");
        playerViewModel.finalizePlayer();
        // Player starts with 65 on normal mode
        assertEquals(65, player.getHealthPoints());
    }

    @Test
    public void testPlayerHardDifficulty() {
        player = Player.getInstance();
        playerViewModel.setPlayerName("Bob");
        playerViewModel.updatePlayerSprite("Sprite 1");
        playerViewModel.updateDifficulty("Hard");
        playerViewModel.finalizePlayer();
        // Player starts with 55 on normal mode
        assertEquals(55, player.getHealthPoints());
    }
    @Test
    public void testPlayerInitialScore() {
        player = Player.getInstance();
        playerViewModel.setPlayerName("Bob");
        playerViewModel.updatePlayerSprite("Sprite 1");
        playerViewModel.updateDifficulty("Easy");
        playerViewModel.finalizePlayer();
        gameViewModel = new InitialGameScreenViewModel();
        assertEquals(100, player.getScore());
    }
    @Test
    public void testPlayerScoreUpdate() {
        player = Player.getInstance();
        playerViewModel.setPlayerName("Bob");
        playerViewModel.updatePlayerSprite("Sprite 1");
        playerViewModel.updateDifficulty("Easy");
        playerViewModel.finalizePlayer();
        gameViewModel = new InitialGameScreenViewModel();
        gameViewModel.updateScore();
        assertEquals(99, player.getScore());
    }
    @Test
    public void testPlayerScoreStopping() {
        player = Player.getInstance();
        playerViewModel.setPlayerName("Bob");
        playerViewModel.updatePlayerSprite("Sprite 1");
        playerViewModel.updateDifficulty("Easy");
        playerViewModel.finalizePlayer();
        gameViewModel = new InitialGameScreenViewModel();
        // Score should stop at 0
        for (int i = 120; i > 0; i++) {
            gameViewModel.updateScore();
        }
        assertEquals(0, player.getScore());
    }


    @Test
    public void testPlayerXCoordinate() {
        player = Player.getInstance();
        player.setCoordinates(150, 150);
        assertEquals(150, player.getXCoordinate());
    }

    @Test
    public void testPlayerYCoordinate() {
        player = Player.getInstance();
        player.setCoordinates(150, 150);
        assertEquals(150, player.getYCoordinate());
    }

    @Test
    public void testScore() {
        score = new Score("Bob", 50, "11:50");
        assertEquals(50, score.getScore());
    }

    @Test
    public void testScoreTime() {
        score = new Score("Bob", 50, "11:50");
        assertEquals("11:50", score.getTime());
    }

    @Test
    public void testCompareTest() {
        Score score2 = new Score("Jerry", 100, "11:50");
        score = new Score("Bob", 50, "11:50");
        // returns 1 if score is less than score comparing to
        assertEquals(1, score.compareTo(score2));
    }

    InitialGameScreen initialGameScreen = new InitialGameScreen();

    @Test
    public void testPlayerMovementLeft() {
        player = player.getInstance();
        player.setCoordinates(40,40);
        InitialGameScreen initialGameScreen = new InitialGameScreen();
        initialGameScreen.onKeyDown(KeyEvent.KEYCODE_A, null);
        assertEquals(30, player.getXCoordinate());
        assertEquals(40, player.getYCoordinate());
    }

    public void testPlayerMovementRight() {
        player = player.getInstance();
        player.setCoordinates(40,40);
        InitialGameScreen initialGameScreen = new InitialGameScreen();
        initialGameScreen.onKeyDown(KeyEvent.KEYCODE_D, null);
        assertEquals(50, player.getXCoordinate());
        assertEquals(40, player.getYCoordinate());
    }

    public void testPlayerMovementDown() {
        player = player.getInstance();
        player.setCoordinates(40,40);
        InitialGameScreen initialGameScreen = new InitialGameScreen();
        initialGameScreen.onKeyDown(KeyEvent.KEYCODE_S, null);
        assertEquals(40, player.getXCoordinate());
        assertEquals(30, player.getYCoordinate());
    }

    public void testPlayerMovementUp() {
        player = player.getInstance();
        player.setCoordinates(40,40);
        InitialGameScreen initialGameScreen = new InitialGameScreen();
        initialGameScreen.onKeyDown(KeyEvent.KEYCODE_W, null);
        assertEquals(40, player.getXCoordinate());
        assertEquals(50, player.getYCoordinate());
    }

}
