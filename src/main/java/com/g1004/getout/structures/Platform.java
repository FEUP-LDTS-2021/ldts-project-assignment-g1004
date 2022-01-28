package com.g1004.getout.structures;

import com.g1004.getout.position.Position;
import com.g1004.getout.element.Wall;

import java.util.ArrayList;
import java.util.List;

public class Platform {
    private final Position left;
    private final Position right;
    private final List<Wall> walls;

    public Platform(Position left, Position right) {
        this.left = left;
        this.right = right;
        walls = createWalls();
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        int n = right.getX() - left.getX() + 1;
        for (int i = 0; i < n; i++)
            walls.add(new Wall(left.getX() + i, left.getY()));

        return walls;
    }

    public Position getLeft() {
        return left;
    }

    public Position getRight() {
        return right;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public boolean hasElement(Position position) {
        return (position.getX() <= right.getX() && position.getX() >= left.getX()) && position.getY() == left.getY() - 1;
    }

}
