package com.example.android.uawebchallenge.ArraySnake;

import java.util.Arrays;

public class GameField {
    int[][] mainGameArray;
    Snake snake;

    public GameField(int[][] gameArray){
        this.mainGameArray = gameArray;
    }

    public void showPretty(){
        for(int i = 0; i < mainGameArray.length; i++){
            System.out.println(Arrays.toString(mainGameArray[i]));
        }
    }

    public void putSnakeIn(Snake snake){
        this.snake = snake;

        for(int i = 0; i < snake.parts.size(); i++){
            mainGameArray[snake.parts.get(i).getX()][snake.parts.get(i).getY()] = 1;
        }
    }

    public void update(){
        mainGameArray[snake.parts.getFirst().getX()][snake.parts.getFirst().getY()] = 1;
        mainGameArray[snake.tail.getX()][snake.tail.getY()] = 0;
    }
}