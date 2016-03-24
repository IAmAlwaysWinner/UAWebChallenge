package com.example.android.uawebchallenge.ArraySnake;

import java.util.Arrays;

public class GameField {
    public int[][] mainGameArray;
    public Snake snake;
    public int fieldHeight;
    public int fieldWidth;
    public Food food;

    public GameField(int[][] gameArray){
        this.mainGameArray = gameArray;
        this.fieldHeight = mainGameArray.length;
        this.fieldWidth = mainGameArray[mainGameArray.length-1].length;
    }

    public void showPretty(){
        for(int i = 0; i < mainGameArray.length; i++){
            for (int k = 0; k < mainGameArray[i].length; ++k)
            {
                System.out.print(mainGameArray[i][k]);
            }
            System.out.println();
//            System.out.println(Arrays.toString(mainGameArray[i]));
        }
        System.out.println("_______________________________________");
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

    public void putFood(){
        int conditionallyPosition = (int) (Math.random()*this.fieldHeight*this.fieldWidth - snake.getLength());
        int tmpPosition = 0;
        for(int i = 0; i < this.fieldHeight; i++){
            for(int j = 0; j < this.fieldWidth; j++){
                if(tmpPosition == conditionallyPosition){
                    this.mainGameArray[i][j] = 2;
                    this.food = new Food(i,j);
                    this.showPretty();
                    return;
                }
                if(this.mainGameArray[i][j]!=1){
                    tmpPosition++;
                }
            }
        }
    }

    public void takeFoodAway(){
        this.mainGameArray[this.food.getX()][this.food.getY()] = 0;
        this.food = null;
    }
}