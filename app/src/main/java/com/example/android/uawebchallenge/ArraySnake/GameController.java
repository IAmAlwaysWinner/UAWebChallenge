package com.example.android.uawebchallenge.ArraySnake;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.uawebchallenge.ArraySnake.Threading.SnakeThread;
import com.example.android.uawebchallenge.MainActivity;
import com.example.android.uawebchallenge.R;

import javax.xml.datatype.Duration;

public class GameController {
    public volatile Snake snake;
    public GameField gameField;
    public int gameSpeed = 500;
    public int foodTime = (10000);
    public MainActivity mainActivity;

    public void gameInitialize(final MainActivity mainActivity){
        //I dont even know how to justify myself for this handler
        Handler accessToUI = new Handler(){
            @Override
            public void handleMessage(Message msg){
                TextView loseTextView = (TextView) mainActivity.findViewById(R.id.loseTextView);
                loseTextView.setVisibility(View.VISIBLE);
                Button startButton = (Button) mainActivity.findViewById(R.id.startButton);
                startButton.setVisibility(View.VISIBLE);
                startButton.setClickable(true);
            }
        };
        this.mainActivity = mainActivity;
        //Snake initialization
        this.snake = initializeSnake();

        //Game Field initialization
        gameField = initializeGameField(18,18);
        gameField.putSnakeIn(snake);
        gameField.showPretty();

        //Creating and starting main game thread
        SnakeThread snakeThread = new SnakeThread(this,accessToUI);
            snakeThread.start();
    }

    //This method checks for 4 Variants of lose
    public boolean checkLose(){
        if(snake.getDirection() == 1 && snake.parts.getFirst().getX() == 0)
            return true;
        if(snake.getDirection() == 3 && snake.parts.getFirst().getX() == gameField.mainGameArray.length-1)
            return true;
        if(snake.getDirection() == 4 && snake.parts.getFirst().getY() == 0)
            return true;
        if(snake.getDirection() == 2 && snake.parts.getFirst().getY() == gameField.mainGameArray[gameField.mainGameArray.length-1].length-1)
            return true;
        return false;
    }

    //Methods for move of snake
    public synchronized void moveNorth(){
        snake.moveNorth();
        gameField.update();
        gameField.showPretty();
    }

    public synchronized void moveWest(){
        snake.moveWest();
        gameField.update();
        gameField.showPretty();
    }

    public synchronized void moveSouth(){
        snake.moveSouth();
        gameField.update();
        gameField.showPretty();
    }

    public synchronized void moveEast(){
        snake.moveEast();
        gameField.update();
        gameField.showPretty();
    }

    //It might throw AOoB exception, need to check, also might be used after checkLose method to prevent AOoB exception
    public synchronized boolean checkIfGrow(){
        if(snake.getDirection() == 1 && gameField.mainGameArray[snake.parts.getFirst().getX()-1][snake.parts.getFirst().getY()] == 2){
            return true;
        }

        if(snake.getDirection() == 2 && gameField.mainGameArray[snake.parts.getFirst().getX()][snake.parts.getFirst().getY()+1] == 2){
            return true;
        }

        if(snake.getDirection() == 3 && gameField.mainGameArray[snake.parts.getFirst().getX()+1][snake.parts.getFirst().getY()] == 2){
            return true;
        }

        if(snake.getDirection() == 4 && gameField.mainGameArray[snake.parts.getFirst().getX()][snake.parts.getFirst().getY()-1] == 2){
            return true;
        }

        return  false;
    }

    //Methods for growth of snake
    public synchronized void moveNorthWithGrowth(){
        snake.growNorth();
        gameField.update();
        gameField.showPretty();
    }

    public synchronized void moveWestWithGrowth(){
        snake.growWest();
        gameField.update();
        gameField.showPretty();
    }

    public synchronized void moveSouthWithGrowth(){
        snake.growSouth();
        gameField.update();
        gameField.showPretty();
    }

    public synchronized void moveEastWithGrowth(){
        snake.growEast();
        gameField.update();
        gameField.showPretty();
    }

    //Snake initialization (hard coded value of length)
    public Snake initializeSnake(){
        //Direction generation built this way to prevent '0' occurence
        int direction = (int) Math.ceil(4 * (1 - Math.random()));
        int snakeStartingLength = 3;
        return new Snake(snakeStartingLength,direction);
    }

    //Game field initialization
    public GameField initializeGameField(int height, int width){
        int[][] emptyGameArray = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                emptyGameArray[i][j] = 0;
            }
        }
        return new GameField(emptyGameArray);
    }

    public void showLoseMessage(){
        mainActivity.findViewById(R.id.loseTextView).setVisibility(View.VISIBLE);
    }

}
