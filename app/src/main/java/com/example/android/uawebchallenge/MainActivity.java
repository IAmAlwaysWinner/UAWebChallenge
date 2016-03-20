package com.example.android.uawebchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.android.uawebchallenge.ArraySnake.GameController;
import com.example.android.uawebchallenge.ArraySnake.GameField;
import com.example.android.uawebchallenge.ArraySnake.Snake;
import com.example.android.uawebchallenge.ArraySnake.Threading.SnakeThread;

public class MainActivity extends AppCompatActivity {

    public GameController gameController = new GameController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.start_game_button);
        button.setOnClickListener(startGameClickListener);


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
            gameController.gameInitialize();
        }
    };

    View.OnClickListener setNorthDirection = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            synchronized (gameController.snake) {
                gameController.snake.setDirection(1);
                gameController.snake.notify();
            }
        }
    };

    View.OnClickListener setWestDirection = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            synchronized (gameController.snake) {
                gameController.snake.setDirection(2);
                gameController.snake.notify();
            }
        }
    };

    View.OnClickListener setSouthDirection = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            synchronized (gameController.snake) {
                gameController.snake.setDirection(3);
                gameController.snake.notify();
            }
        }
    };

    View.OnClickListener setEastDirection = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            synchronized (gameController.snake) {
                gameController.snake.setDirection(4);
                gameController.snake.notify();
            }
        }
    };

}