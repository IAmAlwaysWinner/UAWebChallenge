package com.example.android.uawebchallenge.ArraySnake;

import java.util.LinkedList;

public class Snake implements Movable, Growable {
    /*
    Snake directions:
    1 - NORTH
    2 - WEST
    3 - SOUTH
    4 - EAST
     */

    private int length;
    private volatile int direction;

    public LinkedList<SnakePart> parts;

    SnakePart tail;
    
    public Snake(int startLength, int startDirection){
        this.length = startLength;
        this.direction = startDirection;
        parts = new LinkedList<SnakePart>();
        determineParts(startDirection);
        tail = parts.getLast();
    }

    public void determineParts(int startDirection){
        SnakePart[] snakeParts = new SnakePart[3];
        switch (startDirection){
            case 1:
                snakeParts[0] = new SnakePart(7,9);
                snakeParts[1] = new SnakePart(8,9);
                snakeParts[2] = new SnakePart(9,9);
                for (int i = 0; i < 3 ; i++){
                    parts.add(snakeParts[i]);
                }
                break;
            case 2:
                snakeParts[0] = new SnakePart(9,11);
                snakeParts[1] = new SnakePart(9,10);
                snakeParts[2] = new SnakePart(9,9);
                for (int i = 0; i < 3 ; i++){
                    parts.add(i, snakeParts[i]);
                }
                break;
            case 3:
                snakeParts[0] = new SnakePart(11,9);
                snakeParts[1] = new SnakePart(10,9);
                snakeParts[2] = new SnakePart(9,9);
                for (int i = 0; i < 3 ; i++){
                    parts.add(i, snakeParts[i]);
                }
                break;
            case 4:
                snakeParts[0] = new SnakePart(9,7);
                snakeParts[1] = new SnakePart(9,8);
                snakeParts[2] = new SnakePart(9,9);
                for (int i = 0; i < 3 ; i++){
                    parts.add(i, snakeParts[i]);
                }
                break;
            default:
                System.out.println("Wrong direction");
        }
    }

    /**
     * Movement of my Snake is based on using fast operations of LinkedList (addFirst,removeLast)
     * So, as u can c, each method removes Last item, and adds new item
     * Params in constructor of new SnakePart item is coming from movement direction:
     *  NORTH - X = (current first item X - 1); - Y same with current first item
     *  WEST - X same with current first item; - Y = (current first item Y + 1)
     *  SOUTH - X = (current first item X + 1); - Y same with current first item
     *  EAST - X same with current first item; - Y = (current first item Y - 1)
     */

    @Override
    public void moveNorth() {
        tail = parts.getLast();
        parts.removeLast();
        parts.addFirst(new SnakePart(parts.getFirst().getX() - 1,parts.getFirst().getY()));
    }

    @Override
    public void moveWest() {
        tail = parts.getLast();
        parts.removeLast();
        parts.addFirst(new SnakePart(parts.getFirst().getX(),parts.getFirst().getY() + 1));
    }

    @Override
    public void moveSouth() {
        tail = parts.getLast();
        parts.removeLast();
        parts.addFirst(new SnakePart(parts.getFirst().getX() + 1, parts.getFirst().getY()));
    }

    @Override
    public void moveEast() {
        tail = parts.getLast();
        parts.removeLast();
        parts.addFirst(new SnakePart(parts.getFirst().getX(),parts.getFirst().getY() - 1));
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public int getLength() {
        return length;
    }


    @Override
    public void growNorth() {
        parts.addFirst(new SnakePart(parts.getFirst().getX() - 1,parts.getFirst().getY()));
    }

    @Override
    public void growWest() {
        parts.addFirst(new SnakePart(parts.getFirst().getX(),parts.getFirst().getY() + 1));
    }

    @Override
    public void growSouth() {
        parts.addFirst(new SnakePart(parts.getFirst().getX() + 1, parts.getFirst().getY()));
    }

    @Override
    public void growEast() {
        parts.addFirst(new SnakePart(parts.getFirst().getX(),parts.getFirst().getY() - 1));
    }
}

