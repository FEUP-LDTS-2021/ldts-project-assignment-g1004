import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Goblin extends Monster {
    public Goblin(int x, int y, Platform p) {
        super(x, y, p);
    }

    @Override
    public Position move() {
        // to do
        // moves from one end of the platform to the other and vice versa repetitively
        return new Position();
    }

    @Override
    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#00FF00"));
        screen.enableModifiers(SGR.BOLD);
        screen.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "g");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;

        return (this == o || this.getPosition().equals(((Goblin) o).getPosition()));
    }
}