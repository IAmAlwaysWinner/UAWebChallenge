package com.example.android.uawebchallenge.ArraySnake;

import com.example.android.uawebchallenge.ArraySnake.Threading.SnakeThread;

public class GameController {
    public volatile Snake snake;
    public GameField gameField;
    public int gameSpeed = 500;

    public void gameInitialize(){
        //Snake initialization
        this.snake = new Snake(3,1);

        //Game Field initialization
        int[][] emptyGameArray = new int[18][18];
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 18; j++) {
                emptyGameArray[i][j] = 0;
            }
        }

        gameField = new GameField(emptyGameArray);
        gameField.putSnakeIn(snake);
        gameField.showPretty();

        SnakeThread snakeThread = new SnakeThread(this,snake);
        snakeThread.start();
    }

    //4 Variants of lose
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

//Should use observer pattern to update gameField array, hope I`ll add it soon.
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
}
