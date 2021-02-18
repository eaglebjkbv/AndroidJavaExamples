package com.eaglebjkbv.surfaceviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    GameView gameView;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView=new GameView(context);

        setContentView(gameView);


    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pasused();
    }
}
