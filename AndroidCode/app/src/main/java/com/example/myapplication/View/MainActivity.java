package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button quitButton = findViewById(R.id.button2);
        quitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });

        Button playerConfigButton = findViewById(R.id.button);
        playerConfigButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlayerConfigActivity.class);
                startActivity(intent);
            }
        });

        //        Button endScreenButton = (Button) findViewById(R.id.button3);
        //        endScreenButton.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                openEndScreen();
        //            }
        //        });


    }

    public void openEndScreen() {
        Intent intent = new Intent(this, EndScreen.class);
        startActivity(intent);
    }
}