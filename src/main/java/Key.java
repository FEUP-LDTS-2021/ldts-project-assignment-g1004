import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

/**
 * Key class. When catched, allows hero to unlock door and end level.
 */
public class Key extends Element {
    /**
     * Constructor.
     * @param x coordinate.
     * @param y coordinate.
     */
    public Key(int x, int y) {
        super(x, y);
    }

    /**
     * Draws the key on the screen.
     * @param screen
     */
    @Override
    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#CCCC00"));
        screen.enableModifiers(SGR.BOLD);
        screen.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "F");
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