package com.example.android.uawebchallenge.ArraySnake;

/**
 * Created by Anton on 19.03.2016.
 */
public class Run {

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

        System.out.println("_____________________________________________");
            snake = gameField.getSnake();
            snake.moveNorth();
            gameField.update();
            gameField.showPretty();
        System.out.println("_____________________________________________");
            snake.moveEast();
            gameField.update();
            gameField.showPretty();
    }
}
