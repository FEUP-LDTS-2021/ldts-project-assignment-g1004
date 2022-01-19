package com.g1004.getout;

/**
 * com.g1004.getout.Position class. Describes location of elements.
 */
public class Position {

    private int x; /** Abscissa */
    private int y; /** Ordinate */

    /**
     * Default constructor.
     */
    public Position() {
        this(0, 0);
    }

    /**
     * Constructor.
     * @param x coordinate.
     * @param y coordinate.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Obtain x coordinate.
     * @return abscissa.
     */
    public int getX() {
        return x;
    }

    /**
     * Obtain y coordinate.
     * @return ordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Comparison between positions of two objects, by comparing its coordinates.
     * @param o
     * @return true if the position of both objects is the same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    /**
     * Calculates the distance between positions.
     * @param position
     * @return returns the distance.
     */
    public double distanceTo(Position position) {
        return Math.sqrt(Math.pow(this.x - position.x, 2) + Math.pow(this.y - position.y, 2));
    }

    /**
     * Changes to another position.
     * @param position
     */
    public void moveTo(Position position) {
        this.x = position.getX();
        this.y = position.getY();
    }
}
