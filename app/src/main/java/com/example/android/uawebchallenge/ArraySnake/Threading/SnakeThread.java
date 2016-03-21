package com.example.android.uawebchallenge.ArraySnake.Threading;

import com.example.android.uawebchallenge.ArraySnake.GameController;
import com.example.android.uawebchallenge.ArraySnake.Snake;

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
                        gameController.gameField.putFood();
                        foodFlag = 0;
                    }
                    gameController.wait(gameController.gameSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (gameController.checkLose())
                break;
            switch (gameController.snake.getDirection()){
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
