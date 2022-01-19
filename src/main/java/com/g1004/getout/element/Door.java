package com.g1004.getout.element;

/**
 * Class door. Allows hero to end current level.
 */
public class Door extends Element {
    /**
     * Constructor.
     * @param x coordinate.
     * @param y coordinate.
     */
    public Door(int x, int y) {
        super(x, y, "0", "#0000FF");
    }

    /**
     * Compares the position of the door with the position of an object.
     * @param o
     * @return true if position of both objects is the same.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;

        return (this == o || this.getPosition().equals(((Door) o).getPosition()));
    }
}