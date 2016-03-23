package com.example.android.uawebchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.uawebchallenge.ArraySnake.GameController;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    public GameController gameController = new GameController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(startGameClickListener);


        Button northButton = (Button) findViewById(R.id.northButton);
        Button westButton = (Button) findViewById(R.id.westButton);
        Button southButton = (Button) findViewById(R.id.southButton);
        Button eastButton = (Button) findViewById(R.id.eastButton);

        northButton.setOnClickListener(setNorthDirection);
        westButton.setOnClickListener(setWestDirection);
        southButton.setOnClickListener(setSouthDirection);
        eastButton.setOnClickListener(setEastDirection);

    }

    View.OnClickListener startGameClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            gameController.gameInitialize(MainActivity.this);
            Button startButton = (Button) findViewById(R.id.startButton);
        }
    };

    View.OnClickListener setNorthDirection = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            synchronized (gameController) {
                gameController.snake.setDirection(1);
            }
        }
    };

    View.OnClickListener setWestDirection = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            synchronized (gameController) {
                gameController.snake.setDirection(2);
            }
        }
    };

    View.OnClickListener setSouthDirection = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            synchronized (gameController) {
                gameController.snake.setDirection(3);

            }
        }
    };

    View.OnClickListener setEastDirection = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            synchronized (gameController) {
                gameController.snake.setDirection(4);
            }
        }
    };

}