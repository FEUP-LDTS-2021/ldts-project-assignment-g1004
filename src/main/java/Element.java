import com.googlecode.lanterna.graphics.TextGraphics;

/**
 * Abstract element class. Describes behavior of some components of the game.
 */
public abstract class Element {
    protected Position position;

    /**
     * Constructor.
     * @param x coordinate.
     * @param y coordinate.
     */
    public Element(int x, int y){
        position = new Position(x, y);
    }

    /**
     * Obtain location of the element.
     * @return position where element is.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Change position of the element.
     * @param position that is meant to be attributed to element.
     */
    public void setPosition(Position position) {
        this.position.moveTo(position);
    }

    /**
     * Draws element on the screen.
     * @param screen
     */
    public abstract void draw(TextGraphics screen);
}