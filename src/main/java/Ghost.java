import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Ghost extends Monster {
    public Ghost(int x, int y, Platform p) {
        super(x, y, p);
    }

    @Override
    public void move() {
        int x = position.getX(), y = position.getY();
        int l = platform.getLeft().getX(), r = platform.getRight().getX();

        if (steps == 4) {
            Random random = new Random();
            int s = r - l + 1;
            int n = random.nextInt(s);

            position.moveTo(new Position(l + n, y));
            steps = 0;
        }
        else {
            if (x == r)
                position.moveTo(new Position(l, y));
            else
                position.moveTo(new Position(x + 1, y));

            steps++;
        }
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
