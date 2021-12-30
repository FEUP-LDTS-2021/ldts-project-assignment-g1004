import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Zombie extends Monster {
    public Zombie(int x, int y, Platform p) {
        super(x, y, p);
    }

    @Override
    public void move() {
        int x = position.getX(), y = position.getY();

        if (x == platform.getRight().getX())
            forward = false;
        else if (x == platform.getLeft().getX())
            forward = true;

        if (forward) {
            if (steps == 2) {
                position.moveTo(new Position(x - 1, y));
                steps = 0;
            }
            else {
                position.moveTo(new Position(x + 1, y));
                steps++;
            }
        }
        else {
            if (steps == 2) {
                position.moveTo(new Position(x + 1, y));
                steps = 0;
            }
            else {
                position.moveTo(new Position(x - 1, y));
                steps++;
            }
        }
    }

    @Override
    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#964B00"));
        screen.enableModifiers(SGR.BOLD);
        screen.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "Z");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;

        return (this == o || this.getPosition().equals(((Zombie) o).getPosition()));
    }
}
