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
        while (true){
            synchronized (gameController.snake){
                try {
                    gameController.snake.wait(gameController.gameSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (checkLose())
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

    //4 Variants of lose
    boolean checkLose(){
        if(gameController.snake.getDirection() == 1 && gameController.snake.parts.getFirst().getX() == 0)
            return true;
        if(gameController.snake.getDirection() == 3 && gameController.snake.parts.getFirst().getX() == gameController.gameField.mainGameArray.length-1)
            return true;
        if(gameController.snake.getDirection() == 4 && gameController.snake.parts.getFirst().getY() == 0)
            return true;
        if(gameController.snake.getDirection() == 2 && gameController.snake.parts.getFirst().getY() == gameController.gameField.mainGameArray[gameController.gameField.mainGameArray.length-1].length-1)
            return true;
        return false;
    }
}
