package com.example.android.uawebchallenge.ArraySnake.Threading;

import com.example.android.uawebchallenge.ArraySnake.GameController;
import com.example.android.uawebchallenge.ArraySnake.Snake;

public class SnakeThread extends Thread{
    Thread snakeThread;
    GameController gameController;

    public SnakeThread(GameController gameController,Snake snake){
        snakeThread = new Thread(this, "Main Game Thread");
        this.gameController = gameController;
    }

    @Override
    public void run() {
        while (gameController.snake.parts.getFirst().getX()!=0){
            synchronized (gameController.snake){
                try {
                    gameController.snake.wait(gameController.gameSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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
