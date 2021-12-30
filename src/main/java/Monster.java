import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Monster extends Element {
    public Monster(int x, int y) {
        super(x, y);
    }

    public abstract void draw(TextGraphics screen);
}
