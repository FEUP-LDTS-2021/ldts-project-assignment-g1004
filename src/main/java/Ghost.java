import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Ghost extends Monster {
    public Ghost(int x, int y, Platform p) {
        super(x, y, p);
    }

    @Override
    public Position move() {
        // to do
        // moves 4 steps forward and teleports to a random spot
        // when it reaches an end it teleports to the other instead of changing direction
        return new Position();
    }

    @Override
    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#A9A9A9"));
        screen.enableModifiers(SGR.BOLD);
        screen.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "h");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;

        return (this == o || this.getPosition().equals(((Ghost) o).getPosition()));
    }
}
