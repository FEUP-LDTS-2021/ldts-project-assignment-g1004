/**
 * Abstract element class. Describes behavior of some components of the game.
 */
public abstract class Element {
    protected Position position;     /** Location on the arena */
    protected final String symbol;
    protected final String colour;

    /**
     * Constructor.
     * @param x coordinate.
     * @param y coordinate.
     */
    public Element(int x, int y, String symbol, String colour) {
        position = new Position(x, y);
        this.symbol = symbol;
        this.colour = colour;
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

    public String getSymbol() {
        return symbol;
    }

    public String getColour() {
        return colour;
    }
}