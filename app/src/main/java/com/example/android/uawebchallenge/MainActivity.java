package com.example.android.uawebchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.uawebchallenge.ArraySnake.GameController;
import com.example.android.uawebchallenge.Visualization.OnSwipeTouchListener;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    public GameController gameController = new GameController();
    int amountOfSymbolsPerRow;
    int amountOfRows;
    float xTouch;
    float yTouch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initializeDisplayMetrics();

        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(startGameClickListener);


//        Button northButton = (Button) findViewById(R.id.northButton);
//        Button westButton = (Button) findViewById(R.id.westButton);
//        Button southButton = (Button) findViewById(R.id.southButton);
//        Button eastButton = (Button) findViewById(R.id.eastButton);
//
//        northButton.setOnClickListener(setNorthDirection);
//        westButton.setOnClickListener(setWestDirection);
//        southButton.setOnClickListener(setSouthDirection);
//        eastButton.setOnClickListener(setEastDirection);
        TextView mainTextView = (TextView) findViewById(R.id.main_text_view);
        mainTextView.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
                synchronized (gameController) {
                    gameController.snake.setDirection(1);
                }
            }

            public void onSwipeRight() {
                synchronized (gameController) {
                    gameController.snake.setDirection(2);
                }
            }

            public void onSwipeLeft() {
                synchronized (gameController) {
                    gameController.snake.setDirection(4);
                }
            }

            public void onSwipeBottom() {
                synchronized (gameController) {
                    gameController.snake.setDirection(3);
                }
            }

        });

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        gameController.showPrettyAndroid(MainActivity.this);
                    }
                }, 200);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.xTouch = event.getX();
        this.yTouch = event.getY();
        return true;
    }

    View.OnClickListener startGameClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            gameController.gameInitialize(MainActivity.this, amountOfRows, amountOfSymbolsPerRow);
            Button startButton = (Button) findViewById(R.id.startButton);
            TextView loseTextView = (TextView) findViewById(R.id.loseTextView);
//            Button northButton = (Button) findViewById(R.id.northButton);
//            Button westButton = (Button) findViewById(R.id.westButton);
//            Button southButton = (Button) findViewById(R.id.southButton);
//            Button eastButton = (Button) findViewById(R.id.eastButton);
//            northButton.setVisibility(View.INVISIBLE);
//            northButton.setClickable(true);
//            westButton.setVisibility(View.INVISIBLE);
//            westButton.setClickable(true);
//            southButton.setVisibility(View.INVISIBLE);
//            southButton.setClickable(true);
//            eastButton.setVisibility(View.INVISIBLE);
//            eastButton.setClickable(true);
            loseTextView.setVisibility(View.INVISIBLE);
            startButton.setVisibility(View.INVISIBLE);
            startButton.setClickable(false);
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

    void initializeDisplayMetrics(){
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int DISPLAY_HEIGHT = displayMetrics.heightPixels;
        int DISPLAY_WIDTH = displayMetrics.widthPixels;

        TextView testTextView = (TextView) findViewById(R.id.testTextView);
        testTextView.measure(0,0);
        int SINGLE_LINE_HEIGHT = testTextView.getLineHeight();
        int SINGLE_SYMBOL_WIDTH = testTextView.getMeasuredWidth();
        testTextView.setVisibility(View.INVISIBLE);

        amountOfSymbolsPerRow = DISPLAY_WIDTH/SINGLE_SYMBOL_WIDTH;
        amountOfRows = DISPLAY_HEIGHT/SINGLE_LINE_HEIGHT;
    }
}