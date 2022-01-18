import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

/**
 * Ladder class. Allows the main character to move between platforms through ladders.
 */
public class Ladder {
    private final Position top;    /** position on the top of the ladder */
    private final Position bottom; /** position on the bottom of the ladder */
    private final List<Bar> bars;

    /**
     * Constructor.
     * @param top position.
     * @param bottom position.
     */
    public Ladder(Position top, Position bottom) {
        this.top = top;
        this.bottom = bottom;
        bars = createBars();
    }

    /**
     * Creates the list of bars of the ladder that integrate the constructor.
     * @return list of bars.
     */
    private List<Bar> createBars() {
        List<Bar> bars = new ArrayList<>();

        int n = bottom.getY() - top.getY() + 1;
        for (int i = 0; i < n; i++)
            bars.add(new Bar(top.getX(), top.getY() + i));

        return bars;
    }

    /**
     * Obtain position on the top of the ladder.
     * @return position on the top of the ladder.
     */
    public Position getTop() {
        return top;
    }

    /**
     * Obtain position on the bottom of the ladder.
     * @return position on the bottom of the ladder.
     */
    public Position getBottom() {
        return bottom;
    }

    /**
     * Obtain the list of bars of the ladder.
     * @return list of bars.
     */
    public List<Bar> getBars() {
        return bars;
    }

    public boolean hasElement(Position position) {
        return ((bottom.getX() == position.getX()) && (position.getY() <= bottom.getY()) && (position.getY() >= top.getY()));
    }

}