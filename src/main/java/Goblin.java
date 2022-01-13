import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

/**
 * Goblin monster class.
 * This monster adopts a strategy in which he moves forward to a corner of the platform and then reverses its direction, moving to the other corner.
 */
public class Goblin extends Monster {
    /**
     * Constructor.
     * @param x coordinate.
     * @param y coordinate.
     * @param p platform.
     */
    public Goblin(int x, int y, Platform p) {
        super(x, y, 2, p);
    }

    /**
     * Defines strategy for goblin moves, according to its special features.
     * @return goblin strategy.
     */
    @Override
    protected MoveStrategy createMoveStrategy() {
        return new RegularStrategy();
    }

    /**
     * Draw goblin on the screen.
     * @param screen
     */
    @Override
    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#00FF00"));
        screen.enableModifiers(SGR.BOLD);
        screen.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "g");
    }

    /**
     * Comparison between position of the goblin and position of an object.
     * @param o
     * @return true if the position of both objects is the same.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;

        return (this == o || this.getPosition().equals(((Goblin) o).getPosition()));
    }
}
