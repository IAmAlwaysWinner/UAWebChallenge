package com.example.android.uawebchallenge.ArraySnake;

import com.example.android.uawebchallenge.ArraySnake.Threading.SnakeThread;

import javax.xml.datatype.Duration;

public class GameController {
    public volatile Snake snake;
    public GameField gameField;
    public int gameSpeed = 500;
    public int foodTime = (10000);

    public void gameInitialize(){
        //Snake initialization
        this.snake = initializeSnake();

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

        SnakeThread snakeThread = new SnakeThread(this);
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

    //Duplication of snake methods with console logging(showing) of game
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

    public Snake initializeSnake(){
        //Direction generation built this way to prevent '0' occurence
        int direction = (int) Math.ceil(4 * (1 - Math.random()));
        int snakeStartingLength = 3;
        return new Snake(snakeStartingLength,direction);
    }
}
