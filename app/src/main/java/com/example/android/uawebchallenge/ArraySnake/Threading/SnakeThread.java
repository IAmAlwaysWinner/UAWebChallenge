package com.example.android.uawebchallenge.ArraySnake.Threading;

import android.content.Context;

import com.example.android.uawebchallenge.ArraySnake.GameController;
import com.example.android.uawebchallenge.ArraySnake.Snake;
import com.example.android.uawebchallenge.MainActivity;

public class SnakeThread extends Thread{
    Thread snakeThread;
    GameController gameController;

    public SnakeThread(GameController gameController){
        snakeThread = new Thread(this, "Main Game Thread");
        this.gameController = gameController;
    }

    @Override
    public void run() {
        int foodFlag = 0;
        while (true){
            int foodTimes = gameController.foodTime/gameController.gameSpeed;
            synchronized (gameController){
                try {
                    foodFlag++;
                    if(foodFlag == foodTimes){
                        if(gameController.gameField.food != null){
                            gameController.gameField.takeFoodAway();
                        }
                        gameController.gameField.putFood();
                        foodFlag = 0;
                    }
                    gameController.wait(gameController.gameSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (gameController.checkLose()) {
                break;  
            }
            if(gameController.checkIfGrow()){
                switch (gameController.snake.getDirection()) {
                    case 1:
                        gameController.moveNorthWithGrowth();
                        break;
                    case 2:
                        gameController.moveWestWithGrowth();
                        break;
                    case 3:
                        gameController.moveSouthWithGrowth();
                        break;
                    case 4:
                        gameController.moveEastWithGrowth();
                        break;
                    default:
                        throw new RuntimeException("Wrong direction");
                }
            } else {
                switch (gameController.snake.getDirection()) {
                    case 1:
                        gameController.moveNorth();
                        break;
                    case 2:
                        gameController.moveWest();
                        break;
                    case 3:
                        gameController.moveSouth();
                        break;
                    case 4:
                        gameController.moveEast();
                        break;
                    default:
                        throw new RuntimeException("Wrong direction");
                }
            }
        }
    }
}
