import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

/**
 * Arena class. This class creates an arena or level for the user to play.
 */
public class Arena {
    private final int width;            /** horizontal extent of arena */
    private final int height;           /** vertical extent of arena */
    private char direction;             /** direction of user movement: horizontal or vertical */
    private List<Wall> walls;           /** walls that surround arena */
    private List<Platform> platforms;   /** horizontal platforms inside arena */
    private List<Ladder> ladders;       /** vertical ladders inside arena */
    private List<Coin> coins;           /** coins that can be caught by user */
    private Key key;                    /** key that can be caught by user */
    private Door door;                  /** door for the user to get out of arena */
    private Hero hero;                  /** game character controlled by the user */
    private List<Monster> monsters;     /** monsters that exist and move inside arena */
    private int score;                  /** points got by the user due to caught of coins */

    /**
     * Constructor. It defines a size for the arena and adds all its components.
     * It also initializes user score (zero points) and sets a default value for direction parameter ('0').
     */
    public Arena() {
        width = 60;
        height = 20;
        direction = '0';
        walls = createWalls();
        platforms = createPlatforms();
        ladders = createLadders();
        coins = createCoins();
        key = new Key(2, 3);
        door = new Door(58, 3);
        hero = new Hero(1, 18);
        monsters = createMonsters();
        score = 0;
    }

    /**
     * Creates walls inside arena, delimiting the area for the user to move.
     * @return walls surrounding all the hero movements area.
     */
    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }

        for (int r = 0; r < height; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }

        return walls;
    }

    /**
     * Creates horizontal platforms inside arena.
     * @return platforms that arena contains.
     */
    private List<Platform> createPlatforms() {
        List<Platform> platforms = new ArrayList<>();

        platforms.add(new Platform(new Position(18, 16), new Position(34, 16)));
        platforms.add(new Platform(new Position(6, 13), new Position(48, 13)));
        platforms.add(new Platform(new Position(4, 10), new Position(17, 10)));
        platforms.add(new Platform(new Position(26, 10), new Position(50, 10)));
        platforms.add(new Platform(new Position(10, 7), new Position(30, 7)));
        platforms.add(new Platform(new Position(40, 7), new Position(52, 7)));
        platforms.add(new Platform(new Position(1, 4), new Position(25, 4)));
        platforms.add(new Platform(new Position(48, 4), new Position(58, 4)));

        return platforms;
    }

    /**
     * Creates vertical ladders inside arena.
     * @return ladders that arena contains.
     */
    private List<Ladder> createLadders() {
        List<Ladder> ladders = new ArrayList<>();

        ladders.add(new Ladder(new Position(20, 16), new Position(20, 18)));
        ladders.add(new Ladder(new Position(30, 13), new Position(30, 15)));
        ladders.add(new Ladder(new Position(16, 10), new Position(16, 12)));
        ladders.add(new Ladder(new Position(11, 7), new Position(11, 9)));
        ladders.add(new Ladder(new Position(28, 7), new Position(28, 9)));
        ladders.add(new Ladder(new Position(41, 7), new Position(41, 9)));
        ladders.add(new Ladder(new Position(23, 4), new Position(23, 6)));
        ladders.add(new Ladder(new Position(50, 4), new Position(50, 6)));

        return ladders;
    }

    /**
     * Creates coins inside arena.
     * @return coins that arena contains.
     */
    private List<Coin> createCoins() {
        List<Coin> coins = new ArrayList<>();

        coins.add(new Coin(40, 18));
        coins.add(new Coin(18, 15));
        coins.add(new Coin(31, 15));
        coins.add(new Coin(8, 12));
        coins.add(new Coin(37, 12));
        coins.add(new Coin(32, 9));
        coins.add(new Coin(44, 6));
        coins.add(new Coin(12, 3));

        return coins;
    }

    /**
     * Creates monsters inside arena.
     * @return monsters that arena contains.
     */
    private List<Monster> createMonsters() {
        List<Monster> monsters = new ArrayList<>();

        int x, y;
        for (Platform p : platforms) {
            x = p.getLeft().getX();
            y = p.getLeft().getY();
            if (y == 13)
                monsters.add(new Ghost(x, y - 1, p));
            else if (y % 2 == 0)
                monsters.add(new Goblin(x, y - 1, p));
            else
                monsters.add(new Zombie(x, y - 1, p));
        }

        return monsters;
    }

    /**
     * Draws the arena on the screen, meaning it draws all arena's elements.
     * @param screen
     */
    public void draw(TextGraphics screen) {
        screen.setBackgroundColor(TextColor.Factory.fromString("#F5F5DC"));
        screen.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        for (Wall wall : walls)
            wall.draw(screen);

        for (Platform platform : platforms)
            platform.draw(screen);

        screen.setBackgroundColor(TextColor.Factory.fromString("#F5F5DC"));

        for (Ladder ladder : ladders)
            ladder.draw(screen);

        screen.setBackgroundColor(TextColor.Factory.fromString("#F5F5DC"));

        for (Coin coin : coins)
            coin.draw(screen);

        key.draw(screen);
        hero.draw(screen);
        door.draw(screen);

        for (Monster monster : monsters)
            monster.draw(screen);
    }

    /**
     * Given a user input from the keyboard, moves hero if it is possible, setting a direction for hero's movement.
     * This makes it easier when it comes to dealing with the user's movement permissions:
        * Vertical movement, 'v' -> arrows up and down.
        * Horizontal movement, 'h' -> arrows left and right.
        * In the case of other movements (keys) the hero will not move.
     * @param key pressed by the user.
     */
    public void processKey(KeyStroke key) {
        //System.out.println(key);
        switch (key.getKeyType()) {
            case EOF:
                exit(0);
            case ArrowUp:
                direction = 'v';
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
                direction = 'v';
                moveHero(hero.moveDown());
                break;
            case ArrowLeft:
                direction = 'h';
                moveHero(hero.moveLeft());
                break;
            case ArrowRight:
                direction = 'h';
                moveHero(hero.moveRight());
                break;
            default:
                direction = '0';
        }
    }

    /**
     * Changes hero position, if hero has permission to make that move.
     * @param position is the place to where the user wants the hero to move.
     */
    public void moveHero(Position position) {
        if (canHeroMove(position)) {
            hero.setPosition(position);
            retrieveCoins();
        }
    }

    /**
     * Obtain hero of arena.
     * @return hero.
     */
    public Hero getHero() {
        return hero;
    }

    /**
     * Determines if the hero's movement is possible according to the place where he is and the direction he wants to go:
        * The hero must move inside area delimited with walls.
        * On ladders, the movement can only be vertical, 'v'. Also, the hero can get down to one of them.
        * On platforms or on the floor wall, the movement can only be horizontal, 'h'.
        * In the case of other movements the hero will not move.
     * @param pos to where hero wants to move to.
     * @return true if hero can move or false if hero can´t move.
     */
    private boolean canHeroMove(Position pos) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(pos))
                return false;

        if (direction == 'v') {
            for (Ladder ladder : ladders)
                if (((ladder.getBottom().getX() == pos.getX()) && (pos.getY() <= ladder.getBottom().getY()) && (pos.getY() >= ladder.getTop().getY())) || ladder.getTop().getY() - 1 == pos.getY())
                    return true;
        }
        else if (direction == 'h'){
            for (Platform platform : platforms)
                if ((pos.getX() <= platform.getRight().getX() && pos.getX() >= platform.getLeft().getX()) && pos.getY() == platform.getLeft().getY()-1)
                    return true;

            if (pos.getY() == height-2)
                return true;
        }

        return false;
    }

    /**
     * Moves all the monsters in arena.
     */
    public void moveMonsters() {
        for (Monster monster : monsters)
            monster.move();
    }

    /**
     * Checks if any of the monsters collided with hero, in which case he will die, showing user's score.
     * @return true if hero died, otherwise false.
     */
    public boolean verifyMonsterCollisions() {
        for (Monster monster : monsters) {
            if (monster.getPosition().equals(hero.getPosition())) {
                hero.hurt(monster.attack());
                if (hero.isDead()) {
                    System.out.println("You died... Game over!");
                    System.out.printf("Score: %d", score);
                    return true;
                }
                else {
                    System.out.printf("HP: %d", hero.getHP());
                    System.out.println();
                }
            }
        }
        return false;
    }

    /**
     * Each coin is removed if its position equals hero´s position.
     * Also, provides the score of the hero .
     */
    private void retrieveCoins() {
        for (Coin coin : coins) {
            if (hero.getPosition().equals(coin.getPosition())) {
                coins.remove(coin);
                score++;
                break;
            }
        }
    }

    /**
     * Obtain the game score.
     * @return total score.
     */
    public int getScore() {
        return score;
    }

    private void retrieveKey(){
        //to do
    }

    public boolean leave(){
        //to do
        return true;
    }
}

