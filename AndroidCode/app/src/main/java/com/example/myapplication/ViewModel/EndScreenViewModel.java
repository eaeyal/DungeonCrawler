package com.example.myapplication.ViewModel;

import com.example.myapplication.Model.Player;

public class EndScreenViewModel {
    public EndScreenViewModel() {
    }

    public int getScore() {
        return Player.getInstance().getScore();
    }
}
