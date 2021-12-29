import java.util.ArrayList;
import java.util.List;

public class Ladder {
    private final Position top;
    private final Position bottom;
    private final List<Bar> bars;

    public Ladder(Position top, Position bottom) {
        this.top = top;
        this.bottom = bottom;
        bars = createBars();
    }

    private List<Bar> createBars() {
        List<Bar> bars = new ArrayList<>();
        // to do
        return bars;
    }

    public Position getTop() {
        return top;
    }

    public Position getBottom() {
        return bottom;
    }

    public List<Bar> getBars() {
        return bars;
    }

    public void draw() {
        // to do
    }
}