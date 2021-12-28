import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Ladder {
    private final Position top;
    private final Position bottom;
    private final List<Bar> bars;

    public Ladder(Position top, Position bottom) {
        this.top = top;
        this.bottom = bottom;
        int n = bottom.getY() - top.getY() + 1;

        List<Bar> bars = new ArrayList<>();
        for (int i = 0; i < n; i++)
            bars.add(new Bar(top.getX(), top.getY() + i));
        this.bars = bars;
    }

    public Position getTop() {
        return top;
    }

    public Position getBottom() {
        return bottom;
    }

    public void draw(TextGraphics screen) {
        for (Bar b : bars)
            b.draw(screen);
    }
}
