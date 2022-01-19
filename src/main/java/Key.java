/**
 * Key class. When caught, allows hero to unlock door and end level.
 */
public class Key extends Element {
    /**
     * Constructor.
     * @param x coordinate.
     * @param y coordinate.
     */
    public Key(int x, int y) {
        super(x, y, "F", "#B57B0E");
    }

    /**
     * Comparison between position of the key with position of an object.
     * @param o
     * @return true if the position of both objects is the same.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;

        return (this == o || this.getPosition().equals(((Key) o).getPosition()));
    }
}