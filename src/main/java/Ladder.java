import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Ladder {
    private final Position top;
    private final Position bottom;

    public Ladder(Position top, Position bottom) {
        this.top = top;
        this.bottom = bottom;
    }

    public Position getTop() {
        return top;
    }

    public Position getBottom() {
        return bottom;
    }

    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
        screen.enableModifiers(SGR.BOLD);

        int n = bottom.getY() - top.getY() + 1;
        for (int i = 0; i < n; i++)
            screen.putString(new TerminalPosition(top.getX(), top.getY() + i), "H");
    }
}
