import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

/**
 * Hero class. The main character, which is controlled by the player, can die if he loses all health due to hitting monsters.
 */
public class Hero extends Element {
    /**
     * Constructor.
     * @param x coordinate.
     * @param y coordinate.
     */
    public Hero(int x, int y) {
        super(x, y);
    }

    /**
     * Informs on the future position, to where the player wants to move to, having pressed arrow up key.
     * @return the position updated when the hero moves up one unit.
     */
    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1);
    }

    /**
     * Informs on the future position, to where the player wants to move to, having pressed arrow down key.
     * @return the position updated when the hero moves down one unit.
     */
    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }

    /**
     * Informs on the future position, to where the player wants to move to, having pressed arrow left key.
     * @return the position updated when the hero moves left one unit.
     */
    public Position moveLeft() {
        return new Position(position.getX() - 1, position.getY());
    }

    /**
     * Informs on the future position, to where the player wants to move to, having pressed arrow right key.
     * @return the position updated when the hero moves right one unit.
     */
    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }

    /**
     * Draws hero on the screen.
     * @param screen
     */
    @Override
    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        screen.enableModifiers(SGR.BOLD);
        screen.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "X");
    }
}