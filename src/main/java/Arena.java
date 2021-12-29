import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

public class Arena {
    private final int width;
    private final int height;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void draw(TextGraphics screen) {
        screen.setBackgroundColor(TextColor.Factory.fromString("#F5F5DC"));
        screen.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');
    }

    public void processKey(KeyStroke key) {
        // to do
    }
}
