import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element {
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(TextGraphics screen) {
        screen.setBackgroundColor(TextColor.Factory.fromString("#964B00"));
        screen.putString(new TerminalPosition(position.getX(), position.getY()), " ");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;

        return (this == o || this.getPosition().equals(((Wall) o).getPosition()));
    }
}