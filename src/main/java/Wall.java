/**
 * Wall class. Delimits area where game happens.
 */
public class Wall extends Element {
    /**
     * Constructor.
     * @param x coordinate.
     * @param y coordinate.
     */
    public Wall(int x, int y) {
        super(x, y, " ", "#964B00");
    }

    /**
     *
     * Compares the position of the wall with the position of an object.
     * @param o
     * @return true if the position of both objects is the same.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;

        return (this == o || this.getPosition().equals(((Wall) o).getPosition()));
    }
}