package com.example.android.uawebchallenge.ArraySnake;

/**
 * Created by Anton on 19.03.2016.
 */

/**
 * Movement of my Snake is based on using fast operations of LinkedList (addFirst,removeLast)
 * So, as u can c, each method removes Last item, and adds new item
 * Params in constructor of new SnakePart item is coming from movement direction:
 *  NORTH - X = (current first item X - 1); - Y same with current first item
 *  WEST - X same with current first item; - Y = (current first item Y + 1)
 *  SOUTH - X = (current first item X + 1); - Y same with current first item
 *  EAST - X same with current first item; - Y = (current first item Y - 1)
 */
public class MovementController /*implements Movable*/{

//    @Override
//    public void moveNorth(Snake snake) {
//        snake.parts.removeLast();
//        snake.parts.addFirst(new SnakePart(snake.parts.getFirst().getX() - 1,snake.parts.getFirst().getY()));
//    }
//
//    @Override
//    public void moveWest(Snake snake) {
//        snake.parts.removeLast();
//        snake.parts.addFirst(new SnakePart(snake.parts.getFirst().getX(),snake.parts.getFirst().getY() + 1));
//    }
//
//    @Override
//    public void moveSouth(Snake snake) {
//        snake.parts.removeLast();
//        snake.parts.addFirst(new SnakePart(snake.parts.getFirst().getX() + 1, snake.parts.getFirst().getY()));
//    }
//
//    @Override
//    public void moveEast(Snake snake) {
//        snake.parts.removeLast();
//        snake.parts.addFirst(new SnakePart(snake.parts.getFirst().getX(),snake.parts.getFirst().getY() - 1));
//    }
}
