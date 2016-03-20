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

    public void startGame(){
        while (snake.parts.getFirst().getX()!=0){
            switch (snake.getDirection()){
                case 1:
                    try {
                        Thread.currentThread().sleep(gameSpeed);
                    } catch (InterruptedException e) {
                        break;
                    }
                    moveNorth();
                    break;
                case 2:
                    try {
                        Thread.currentThread().sleep(gameSpeed);
                    } catch (InterruptedException e) {
                        break;
                    }
                    moveWest();
                    break;
                case 3:
                    try {
                        Thread.currentThread().sleep(gameSpeed);
                    } catch (InterruptedException e) {
                        break;
                    }
                    moveSouth();
                    break;
                case 4:
                    try {
                        Thread.currentThread().sleep(gameSpeed);
                    } catch (InterruptedException e) {
                        break;
                    }
                    moveEast();
                    break;
                default:
                    throw new RuntimeException("Wrong direction");
            }
        }
    }

//Should use observer pattern to update gameField array, hope I`ll add it soon.
    public synchronized void moveNorth(){
        snake.moveNorth();
        gameField.update();
        gameField.showPretty();
        System.out.println("_____________________________________________");
    }

    public synchronized void moveWest(){
        snake.moveWest();
        gameField.update();
        gameField.showPretty();
        System.out.println("_____________________________________________");
    }

    public synchronized void moveSouth(){
        snake.moveSouth();
        gameField.update();
        gameField.showPretty();
        System.out.println("_____________________________________________");
    }

    public synchronized void moveEast(){
        snake.moveEast();
        gameField.update();
        gameField.showPretty();
        System.out.println("_____________________________________________");
    }
}
