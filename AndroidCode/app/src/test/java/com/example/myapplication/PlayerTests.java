package com.example.myapplication;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import com.example.myapplication.Model.Player;
import com.example.myapplication.Model.Score;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;

public class PlayerTests {

    private Player easyPlayer = new Player("easy", 1, 1);
    private Player mediumPlayer = new Player("medium", 2, 1);
    private Player hardPlayer = new Player("hard", 3, 1);
    private Score score = new Score("easy");

    @Test
    public void testStartingHealthForDifficulty1() {
        // Assert that the starting health for difficulty 1 is 75
        assertEquals(75, easyPlayer.getHealthPoints());
        assertEquals(65, mediumPlayer.getHealthPoints());
        assertEquals(55, hardPlayer.getHealthPoints());
    }

    @Test
    public void testPlayerDefaultCoordinates() {
        assertEquals(150, easyPlayer.getX());
        assertEquals(150, easyPlayer.getY());
    }

    @Test
    public void testDefaultScore() {
        assertEquals(100, score.getScore());
    }

}
