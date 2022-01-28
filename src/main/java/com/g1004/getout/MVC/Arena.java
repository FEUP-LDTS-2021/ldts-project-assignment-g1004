package com.g1004.getout.MVC;

import com.g1004.getout.structures.Ladder;
import com.g1004.getout.structures.Platform;
import com.g1004.getout.MVC.builder.*;
import com.g1004.getout.element.*;
import com.g1004.getout.element.monster.Monster;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private ArenaBuilder builder;
    private final int width;
    private final int height;
    private final int numLevel;
    private Integer score;
    private List<Wall> walls;
    private Hero hero;
    private final String background;
    private List<Platform> platforms;
    private List<Ladder> ladders;
    private List<Coin> coins;
    private Key key;
    private Door door;
    private List<Monster> monsters;

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

    public Integer score() {
        return score;
    }

    public List<Wall> walls() {
        return walls;
    }

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

