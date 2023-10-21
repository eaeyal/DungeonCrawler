package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.myapplication.R;
import com.example.myapplication.ViewModel.PlayerConfigActivityViewModel;

public class PlayerConfigActivity extends AppCompatActivity {
    private PlayerConfigActivityViewModel viewModel;

    ImageView playerSprite; /*= findViewById(R.id.playerSprite);

    private ImageView findViewById(int playerSprite) {
        return playerSpriteA;
    }
    */

    protected void checkShouldEnableBegin() {
        Button startGameButton = findViewById(R.id.btnStartGame);
        startGameButton.setEnabled(viewModel.revalidateInput());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_config);


        viewModel = new PlayerConfigActivityViewModel();

        EditText username = findViewById(R.id.playerNameLineEdit);
        Button startGameButton = findViewById(R.id.btnStartGame);
        startGameButton.setEnabled(false);

        RadioGroup difficultySelect = findViewById(R.id.radioGroupDifficultySelect);
        RadioGroup spriteSelect = findViewById(R.id.radioGroupSpriteSelect);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.finalizePlayer();

                Intent intent = new Intent(PlayerConfigActivity.this, InitialGameScreen.class);
                startActivity(intent);
            }
        });

        difficultySelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                viewModel.updateDifficulty((String) radioButton.getText());
                checkShouldEnableBegin();
            }
        });

        spriteSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i("", "Check changed");
                RadioButton radioButton = findViewById(checkedId);
                viewModel.updatePlayerSprite((String) radioButton.getText());
                checkShouldEnableBegin();
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
                viewModel.setPlayerName(username.getText().toString());
                checkShouldEnableBegin();
            }
        });
    }
}