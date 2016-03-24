package com.example.android.uawebchallenge.ArraySnake.Threading;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.android.uawebchallenge.ArraySnake.GameController;
import com.example.android.uawebchallenge.ArraySnake.Snake;
import com.example.android.uawebchallenge.MainActivity;


public class SnakeThread extends Thread{
    Thread snakeThread;
    GameController gameController;
    Handler accessToUI;

    public SnakeThread(GameController gameController, Handler accessToUI){
        snakeThread = new Thread(this, "Main Game Thread");
        this.gameController = gameController;
        this.accessToUI = accessToUI;
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
                Bundle bundle = new Bundle();
                bundle.putInt("do", 2);
                Message msg = new Message();
                msg.setData(bundle);
                accessToUI.sendMessage(msg);
                break;  
            }
            if(gameController.checkIfGrow()){
                switch (gameController.snake.getDirection()) {
                    case 1:
                        gameController.moveNorthWithGrowth();
                        show();
                        break;
                    case 2:
                        gameController.moveWestWithGrowth();
                        show();
                        break;
                    case 3:
                        gameController.moveSouthWithGrowth();
                        show();
                        break;
                    case 4:
                        gameController.moveEastWithGrowth();
                        show();
                        break;
                    default:
                        throw new RuntimeException("Wrong direction");
                }
            } else {
                switch (gameController.snake.getDirection()) {
                    case 1:
                        gameController.moveNorth();
                        show();
                        break;
                    case 2:
                        gameController.moveWest();
                        show();
                        break;
                    case 3:
                        gameController.moveSouth();
                        show();
                        break;
                    case 4:
                        gameController.moveEast();
                        show();
                        break;
                    default:
                        throw new RuntimeException("Wrong direction");
                }
            }
        }
    }

    void show(){
        Bundle bundle = new Bundle();
        bundle.putInt("do", 1);
        Message msg = new Message();
        msg.setData(bundle);
        accessToUI.sendMessage(msg);
    }
}
