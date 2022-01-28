package com.g1004.getout.element;

import com.g1004.getout.position.Position;

public class Hero extends Element {
    private int healthPoints;
    private boolean key;

    public Hero(int x, int y) {
        super(x, y, "X", "#FF0000");
        healthPoints = 20;
        key = false;
    }

    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1);
    }

    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }

    public Position moveLeft() {
        return new Position(position.getX() - 1, position.getY());
    }

    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }

    public int getHP() {
        return healthPoints;
    }

    public void hurt(int damage) {
        healthPoints -= damage;
    }

    public boolean isDead() {
        return healthPoints <= 0;
    }

    public void catchKey(){key = true;}

    public boolean hasKey(){
        return key;
    }
}