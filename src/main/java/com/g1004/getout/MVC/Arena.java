package com.g1004.getout.MVC;

import com.g1004.getout.structures.Ladder;
import com.g1004.getout.structures.Platform;
import com.g1004.getout.MVC.builder.*;
import com.g1004.getout.element.*;
import com.g1004.getout.element.monster.Monster;

import java.util.ArrayList;
import java.util.List;

/**
 * com.g1004.getout.MVC.Arena class. This class creates an arena or level for the user to play.
 */
public class Arena {
    private ArenaBuilder builder;
    private final int width;
    private final int height;
    private final int numLevel;
    private Integer score;                  /** points got by the user due to caught of coins */
    private List<Wall> walls;           /** walls that surround arena */
    private Hero hero;                  /** game character controlled by the user */
    private final String background;
    private List<Platform> platforms;   /** horizontal platforms inside arena */
    private List<Ladder> ladders;       /** vertical ladders inside arena */
    private List<Coin> coins;           /** coins that can be caught by user */
    private Key key;                    /** key that can be caught by user */
    private Door door;                  /** door for the user to get out of arena */
    private List<Monster> monsters;     /** monsters that exist and move inside arena */

    /**
     * Constructor. It defines a size for the arena and adds all its components.
     * It also initializes user score (zero points) and sets a default value for direction parameter ('0').
     */
    public Arena(int numLevel) {
        builder = new GoblinLair(); // so builder is never null
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
        width = 75;
        height = 27;
        this.numLevel = numLevel;
        score = 0;
        walls = generateWalls();
        hero = generateHero();
        background = builder.createBackground();
        platforms = builder.createPlatforms();
        ladders = builder.createLadders();
        coins = builder.createCoins();
        key = builder.createKey();
        door = builder.createDoor();
        monsters = builder.createMonsters();
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

    public void setScore(Integer score) {
        this.score = score;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
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

