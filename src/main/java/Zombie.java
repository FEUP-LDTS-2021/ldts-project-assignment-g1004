import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Zombie extends Monster {
    public Zombie(int x, int y, Platform p) {
        super(x, y, p);
    }

    @Override
    public Position move() {
        // to do
        // moves 2 steps forward 1 backward 2 forward 1 backword and so on from one end of the platform to the other
        return new Position();
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
