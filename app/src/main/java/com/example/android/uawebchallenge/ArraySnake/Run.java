package com.example.android.uawebchallenge.ArraySnake;

/**
 * Created by Anton on 19.03.2016.
 */
public class Run {

    int flag = 0;
    public void startGame(){
        //Snake initialization
        Snake snake = new Snake(3,1);

        //Game Field initialization
        int[][] emptyGameArray = new int[18][18];
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 18; j++) {
                emptyGameArray[i][j] = 0;
            }
        }

        GameField gameField = new GameField(emptyGameArray);
        gameField.putSnakeIn(snake);
        gameField.showPretty();

        flag +=1;
        System.out.println("_____________________________________________");
        if(flag == 1) {
            snake = gameField.getSnake();
            snake.moveNorth();
            gameField.lookAtSnake();
            gameField.showPretty();
        }
    }
}
