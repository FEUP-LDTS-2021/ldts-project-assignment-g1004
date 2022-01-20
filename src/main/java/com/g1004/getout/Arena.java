package com.g1004.getout;

import com.g1004.getout.element.*;
import com.g1004.getout.element.monster.Boss;
import com.g1004.getout.element.monster.Goblin;
import com.g1004.getout.element.monster.Monster;
import com.g1004.getout.gui.GUI;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.exit;

/**
 * com.g1004.getout.Arena class. This class creates an arena or level for the user to play.
 */
public class Arena {
    private final GUI gui;
    private final int width;
    private final int height;
    private final int numLevel;
    private char direction;             /** direction of user movement: horizontal or vertical */
    private List<Wall> walls;           /** walls that surround arena */
    private List<Platform> platforms;   /** horizontal platforms inside arena */
    private List<Ladder> ladders;       /** vertical ladders inside arena */
    private List<Coin> coins;           /** coins that can be caught by user */
    private Key key;                    /** key that can be caught by user */
    private Door door;                  /** door for the user to get out of arena */
    private Hero hero;                  /** game character controlled by the user */
    private List<Monster> monsters;     /** monsters that exist and move inside arena */
    private Integer score;                  /** points got by the user due to caught of coins */

    /**
     * Constructor. It defines a size for the arena and adds all its components.
     * It also initializes user score (zero points) and sets a default value for direction parameter ('0').
     */
    public Arena(GUI gui, int numLevel) {
        this.gui = gui;
        width = gui.getWidth();
        height = gui.getHeight();
        this.numLevel = numLevel;
        direction = '0';
        walls = createWalls();
        platforms = createPlatforms();
        ladders = createLadders();
        coins = createCoins();
        key = createKey();
        door = createDoor();
        hero = createHero();
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
            walls.add(new Wall(c, height - 2));
            walls.add(new Wall(c, height - 3));
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

        if (numLevel == 1) {
            platforms.add(new Platform(new Position(15, 20), new Position(55, 20)));
            platforms.add(new Platform(new Position(10, 16), new Position(30, 16)));
            platforms.add(new Platform(new Position(40, 16), new Position(60, 16)));
            platforms.add(new Platform(new Position(5, 12), new Position(20, 12)));
            platforms.add(new Platform(new Position(25, 12), new Position(47, 12)));
            platforms.add(new Platform(new Position(52, 12), new Position(65, 12)));
            platforms.add(new Platform(new Position(1, 8), new Position(32, 8)));
            platforms.add(new Platform(new Position(54, 8), new Position(73, 8)));
        }
        else if (numLevel == 2) {}
        else if (numLevel == 3) {}
        else if (numLevel == 4) {}
        else if (numLevel == 5) {}
        else if (numLevel == 6) {}
        else if (numLevel == 7) {}
        else if (numLevel == 8) {}
        else if (numLevel == 9) {}
        else if (numLevel == 10) {
            platforms.add(new Platform(new Position(20, 16), new Position(52, 16)));
            platforms.add(new Platform(new Position(15, 13), new Position(55, 13)));
            platforms.add(new Platform(new Position(20, 10), new Position(39, 10)));
            platforms.add(new Platform(new Position(27, 7), new Position(50, 7)));
            platforms.add(new Platform(new Position(30, 20), new Position(40, 20)));
            platforms.add(new Platform(new Position(48, 21), new Position(73, 21)));
            platforms.add(new Platform(new Position(4, 4), new Position(22, 4)));
        }

        return platforms;
    }

    /**
     * Creates vertical ladders inside arena.
     * @return ladders that arena contains.
     */
    private List<Ladder> createLadders() {
        List<Ladder> ladders = new ArrayList<>();

        if (numLevel == 1) {
            ladders.add(new Ladder(new Position(35, 20), new Position(35, 23)));
            ladders.add(new Ladder(new Position(20, 16), new Position(20, 19)));
            ladders.add(new Ladder(new Position(50, 16), new Position(50, 19)));
            ladders.add(new Ladder(new Position(15, 12), new Position(15, 15)));
            ladders.add(new Ladder(new Position(43, 12), new Position(43, 15)));
            ladders.add(new Ladder(new Position(55, 12), new Position(55, 15)));
            ladders.add(new Ladder(new Position(10, 8), new Position(10, 11)));
            ladders.add(new Ladder(new Position(29, 8), new Position(29, 11)));
            ladders.add(new Ladder(new Position(60, 8), new Position(60, 11)));
        }
        else if (numLevel == 2) {}
        else if (numLevel == 3) {}
        else if (numLevel == 4) {}
        else if (numLevel == 5) {}
        else if (numLevel == 6) {}
        else if (numLevel == 7) {}
        else if (numLevel == 8) {}
        else if (numLevel == 9) {}
        else if (numLevel == 10) {
            ladders.add(new Ladder(new Position(33, 20), new Position(33, 23)));
            ladders.add(new Ladder(new Position(37, 16), new Position(37, 19)));
            ladders.add(new Ladder(new Position(49, 16), new Position(49, 20)));
            ladders.add(new Ladder(new Position(24, 13), new Position(24, 15)));
            ladders.add(new Ladder(new Position(48, 7), new Position(48, 12)));
            ladders.add(new Ladder(new Position(21, 4), new Position(21, 9)));
            ladders.add(new Ladder(new Position(34, 7), new Position(34, 9)));
        }

        return ladders;
    }

    /**
     * Creates coins inside arena.
     * @return coins that arena contains.
     */
    private List<Coin> createCoins() {
        List<Coin> coins = new ArrayList<>();

        if (numLevel == 1) {
            coins.add(new Coin(45, 23));
            coins.add(new Coin(27, 19));
            coins.add(new Coin(28, 15));
            coins.add(new Coin(58, 15));
            coins.add(new Coin(7, 11));
            coins.add(new Coin(26, 11));
            coins.add(new Coin(63, 11));
            coins.add(new Coin(15, 7));
            coins.add(new Coin(22, 7));
            coins.add(new Coin(56, 7));
        }
        else if (numLevel == 2) {}
        else if (numLevel == 3) {}
        else if (numLevel == 4) {}
        else if (numLevel == 5) {}
        else if (numLevel == 6) {}
        else if (numLevel == 7) {}
        else if (numLevel == 8) {}
        else if (numLevel == 9) {}
        else if (numLevel == 10) {}

        return coins;
    }

    private Key createKey() {
        if (numLevel == 10)
            return new Key(7, 3);

        return new Key(2, 7);
    }

    private Door createDoor() {
        if (numLevel == 10)
            return new Door(73, 20);

        return new Door(73, 7);
    }

    private Hero createHero() {
        return new Hero(1, height - 4);
    }

    /**
     * Creates monsters inside arena.
     * @return monsters that arena contains.
     */
    private List<Monster> createMonsters() {
        List<Monster> monsters = new ArrayList<>();

        if (numLevel == 1) {
            for (Platform p : platforms)
                monsters.add(new Goblin(p.getLeft().getX(), p.getLeft().getY() - 1, p));
        }
        else if (numLevel == 2) {}
        else if (numLevel == 3) {}
        else if (numLevel == 4) {}
        else if (numLevel == 5) {}
        else if (numLevel == 6) {}
        else if (numLevel == 7) {}
        else if (numLevel == 8) {}
        else if (numLevel == 9) {}
        else if (numLevel == 10) {
            monsters.add(new Boss(platforms.get(0).getLeft().getX(), platforms.get(0).getLeft().getY() - 1, platforms.get(0)));
        }

        return monsters;
    }

    public void draw() {
        String colour = "";

        if (numLevel == 1)
            colour = "#90EE90";
        else if (numLevel == 2)
            colour = "#A9A9A9";
        else if (numLevel == 3) {}
        else if (numLevel == 4) {}
        else if (numLevel == 5) {}
        else if (numLevel == 6) {}
        else if (numLevel == 7) {}
        else if (numLevel == 8) {}
        else if (numLevel == 9) {}
        else if (numLevel == 10) {
            colour = "#C99C99";
        }

        gui.drawBackground(colour);
        gui.drawWalls(walls);

        for (Platform platform : platforms)
            gui.drawPlatform(platform);

        boolean h = false;
        for (Ladder ladder : ladders) {
            gui.drawLadder(ladder);

            if (ladder.hasElement(hero.getPosition()))
                h = true;
        }

        gui.setBGColour(colour);

        if (!hero.hasKey())
            gui.drawKey(key);
        gui.drawDoor(door);

        for (Coin coin : coins)
            gui.drawCoin(coin);

        gui.drawHero(hero, h);
        gui.setBGColour(colour);

        for (Monster monster : monsters) {
            boolean m = false;

            if (monster.getSymbol().length() > 1) {
                int n = 0;
                for (Ladder l : ladders) {
                    for (int i = 0; i < monster.getSymbol().length(); i++) {
                        if (l.hasElement(new Position(monster.getPosition().getX() + i, monster.getPosition().getY()))) {
                            m = true;
                            n = i;
                            break;
                        }
                    }

                    if (m)
                        break;
                }

                gui.drawMonster(monster, m, n);
            }
            else {
                for (Ladder l : ladders) {
                    if (l.hasElement(monster.getPosition())) {
                        m = true;
                        break;
                    }
                }

                gui.drawMonster(monster, m);
            }

            gui.setBGColour(colour);
        }

        gui.drawHealthBar(hero);
        gui.drawScore(score);
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
            retrieveKey();
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
                if (ladder.hasElement(pos) || ladder.getTop().getY() - 1 == pos.getY())
                    return true;
        }
        else if (direction == 'h'){
            for (Platform platform : platforms)
                if (platform.hasElement(pos))
                    return true;

            if (pos.getY() == height - 4)
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

    public void changeBossSpot() {
        Random random = new Random();
        int n = random.nextInt(4);

        monsters.get(0).switchPlatform(platforms.get(n));
    }

    /**
     * Checks if any of the monsters collided with hero, in which case he will die, showing user's score.
     * @return true if hero died, otherwise false.
     */
    public boolean verifyMonsterCollisions() {
        if (monsters.get(0) instanceof Boss) {
            for (int i = 0; i < 13; i++) {
                Position p = new Position(monsters.get(0).getPosition().getX() + i, monsters.get(0).getPosition().getY());
                if (p.equals(hero.getPosition())) {
                    hero.hurt(monsters.get(0).attack());
                    if (hero.isDead()) {
                        loss();
                        return true;
                    }
                }
            }
        }
        else {
            for (Monster monster : monsters) {
                if (monster.getPosition().equals(hero.getPosition())) {
                    hero.hurt(monster.attack());
                    if (hero.isDead()) {
                        loss();
                        return true;
                    }
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
    public Integer getScore() {
        return score;
    }

    private void retrieveKey(){
        if (hero.getPosition().equals(key.getPosition()))
            hero.catchKey();
    }

    public boolean leave() {
        if (hero.hasKey() && hero.getPosition().equals(door.getPosition())){
            win();
            return true;
        }
        return false;
    }

    private void win() {
        gui.drawVictory();
    }

    private void loss() {
        gui.drawDefeat();
    }
}

