/**
 * Coin class. Can be caught by hero, incrementing score.
 */
public class Coin extends Element {
    /**
     * Constructor.
     * @param x coordinate.
     * @param y coordinate.
     */
    public Coin(int x, int y) {
        super(x, y, "$", "#B5A710");
    }

    /**
     * Comparison between position of the coin with position of an object.
     * @param o
     * @return true if the position of both objects is the same.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;

        return (this == o || this.getPosition().equals(((Coin) o).getPosition()));
    }
}