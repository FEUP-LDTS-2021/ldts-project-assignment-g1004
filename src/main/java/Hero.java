import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero {
    private Position position;

    public Hero(int x, int y) {
        position = new Position(x, y);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public Position moveUp() {
        // to do
        return new Position();
    }

    public Position moveDown() {
        // to do
        return new Position();
    }

    public Position moveLeft() {
        // to do
        return new Position();
    }

    public Position moveRight() {
        // to do
        return new Position();
    }

    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        screen.enableModifiers(SGR.BOLD);
        screen.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "X");
    }
}