/**
 * Hero class. The main character, which is controlled by the player, can die if he loses all health due to hitting monsters.
 */
public class Hero extends Element {
    private int healthPoints;
    private boolean key;
    /**
     * Constructor.
     * @param x coordinate.
     * @param y coordinate.
     */
    public Hero(int x, int y) {
        super(x, y, "X", "#FF0000");
        healthPoints = 20;
        key = false;
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

    public int getHP() {
        return healthPoints;
    }

    public void hurt(int damage) {
        healthPoints -= damage;
    }

    public boolean isDead() {
        return healthPoints <= 0;
    }

    public void catchKey(){key = true;}

    public boolean hasKey(){
        return key;
    }
}