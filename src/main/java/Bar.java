import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

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
        super(x, y);
    }

    /**
     * Draws bars.
     * @param screen
     */
    @Override
    public void draw(TextGraphics screen) {
        screen.setBackgroundColor(TextColor.Factory.fromString("#FFA500"));
        screen.putString(new TerminalPosition(position.getX(), position.getY()), " ");
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