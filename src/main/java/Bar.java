/**
 * Bar class. This class creates and draws each ladder bar.
 */
public class Bar extends Element {
    /**
     * Constructor.
     * @param x coordinate.
     * @param y coordinate.
     */
    public Bar(int x, int y) {
        super(x, y, " ", "#FFA500");
    }

    /**
     * Comparison between position of the bar with position of an object.
     * @param o
     * @return true if the position of both objects is the same.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;

        return (this == o || this.getPosition().equals(((Bar) o).getPosition()));
    }
}