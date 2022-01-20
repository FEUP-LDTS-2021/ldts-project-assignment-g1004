package com.g1004.getout;

import com.g1004.getout.builder.*;
import com.g1004.getout.element.*;
import com.g1004.getout.element.monster.Boss;
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
    private ArenaBuilder builder;
    private final GUI gui;
    private final int width;
    private final int height;
    private final int numLevel;
    private char direction;             /** direction of user movement: horizontal or vertical */
    private final String background;
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
        switch (numLevel) {
            case 1 -> builder = new GoblinLair();
            case 2 -> builder = new TheUndead();
            case 3 -> builder = new LostRuins();
            case 4 -> builder = new HauntedMansion();
            case 5 -> builder = new Necropolis();
            case 6 -> builder = new DarkForest();
            case 7 -> builder = new Dystopia();
            case 8 -> builder = new TheShimmer();
            case 9 -> builder = new Apocalypse();
            case 10 -> builder = new LastChance();
        }
        this.gui = gui;
        width = gui.getWidth();
        height = gui.getHeight();
        this.numLevel = numLevel;
        direction = '0';
        background = builder.createBackground();
        walls = generateWalls();
        platforms = builder.createPlatforms();
        ladders = builder.createLadders();
        coins = builder.createCoins();
        key = builder.createKey();
        door = builder.createDoor();
        hero = generateHero();
        monsters = builder.createMonsters();
        score = 0;
    }

    /**
     * Creates walls inside arena, delimiting the area for the user to move.
     * @return walls surrounding all the hero movements area.
     */
    private List<Wall> generateWalls() {
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

    private Hero generateHero() {
        return new Hero(1, height - 4);
    }

    public void draw() {
        gui.drawBackground(background);
        gui.drawWalls(walls);

        for (Platform platform : platforms)
            gui.drawPlatform(platform);

        boolean h = false;
        for (Ladder ladder : ladders) {
            gui.drawLadder(ladder);

            if (ladder.hasElement(hero.getPosition()))
                h = true;
        }

        gui.setBGColour(background);

        if (!hero.hasKey())
            gui.drawKey(key);
        gui.drawDoor(door);

        for (Coin coin : coins)
            gui.drawCoin(coin);

        gui.drawHero(hero, h);
        gui.setBGColour(background);

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

            gui.setBGColour(background);
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

    /**
     * Obtain the game score.
     * @return total score.
     */
    public Integer score() {
        return score;
    }

    public List<Wall> walls() {
        return walls;
    }

    /**
     * Obtain hero of arena.
     * @return hero.
     */
    public Hero hero() {
        return hero;
    }

    public String background() {
        return background;
    }

    public List<Platform> platforms() {
        return platforms;
    }

    public List<Ladder> ladders() {
        return ladders;
    }

    public List<Coin> coins() {
        return coins;
    }

    public Key key() {
        return key;
    }

    public Door door() {
        return door;
    }

    public List<Monster> monsters() {
        return monsters;
    }
}

