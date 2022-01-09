import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

/**
 * Ghost monster class.
 * This monster is like a ghost, adopts a strategy in which he disappears and changes its position in the platform to a random one.
 */
public class Ghost extends Monster {
    /**
     * Constructor.
     * @param x coordinate.
     * @param y coordinate.
     * @param p platform.
     */
    public Ghost(int x, int y, Platform p) {
        super(x, y, p);
    }

    /**
     * Defines strategy for ghost moves, according to its special features.
     * @return ghost strategy.
     */
    @Override
    protected MoveStrategy createMoveStrategy() {
        return new TeleportationStrategy();
    }

    /**
     * Draws ghost on the screen.
     * @param screen
     */
    @Override
    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#A9A9A9"));
        screen.enableModifiers(SGR.BOLD);
        screen.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "h");
    }

    /**
     * Comparison between position of the ghost and position of an object.
     * @param o
     * @return true if the position of both objects is the same.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;

        return (this == o || this.getPosition().equals(((Ghost) o).getPosition()));
    }
}