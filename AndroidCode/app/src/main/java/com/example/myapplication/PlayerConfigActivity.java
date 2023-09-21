package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class PlayerConfigActivity extends AppCompatActivity {
    private static HashMap<String, Integer> diffStr2Int = new HashMap<String, Integer>(){
        {
            put("Easy", 1);
            put("Normal", 2);
            put("Hard", 3);
        }
    };

    private static HashMap<String, Integer> sprite2Int = new HashMap<String, Integer>(){
        {
            put("Sprite 1", 1);
            put("Sprite 2", 2);
            put("Sprite 3", 3);
        }
    };

    protected static int any2int(String d, HashMap<String, Integer> b) {
        return b.get(d);
    }

    // assuming the activity don't get recreated on re-render like flutter does
    // we can keep states here
    Integer difficulty = null;
    Integer playerSprite = null;
    String playerName = null;

    protected void revalidateInput() {
        if (difficulty != null && playerSprite != null && playerName != null &&
                !playerName.isEmpty() && !playerName.trim().isEmpty()) {
            Button startGameButton = findViewById(R.id.btnStartGame);

            startGameButton.setEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_config);

        EditText username = findViewById(R.id.playerNameLineEdit);
        Button startGameButton = findViewById(R.id.btnStartGame);
        startGameButton.setEnabled(false);

        RadioGroup difficultySelect = findViewById(R.id.radioGroupDifficultySelect);
        RadioGroup spriteSelect = findViewById(R.id.radioGroupSpriteSelect);

        difficultySelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                difficulty = any2int((String) radioButton.getText(), diffStr2Int);
                revalidateInput();
            }
        });

        spriteSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i("", "Check changed");
                RadioButton radioButton = findViewById(checkedId);
                playerSprite = any2int((String) radioButton.getText(), sprite2Int);
                revalidateInput();
            }
        });

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("", s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                playerName = username.getText().toString();
                revalidateInput();
            }
        });
    }
}