import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

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
        super(x, y);
    }

    /**
     * Draws wall on the screen.
     * @param screen
     */
    @Override
    public void draw(TextGraphics screen) {
        screen.setBackgroundColor(TextColor.Factory.fromString("#964B00"));
        screen.putString(new TerminalPosition(position.getX(), position.getY()), " ");
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