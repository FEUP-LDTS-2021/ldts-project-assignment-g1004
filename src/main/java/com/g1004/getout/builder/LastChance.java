package com.g1004.getout.builder;

import com.g1004.getout.Ladder;
import com.g1004.getout.Platform;
import com.g1004.getout.Position;
import com.g1004.getout.element.Coin;
import com.g1004.getout.element.Door;
import com.g1004.getout.element.Key;
import com.g1004.getout.element.monster.Boss;
import com.g1004.getout.element.monster.Monster;

import java.util.ArrayList;
import java.util.List;

public class LastChance implements ArenaBuilder {
    @Override
    public String createBackground() {
        return "#C99C99";
    }

    @Override
    public List<Platform> createPlatforms() {
        List<Platform> platforms = new ArrayList<>();

        platforms.add(new Platform(new Position(20, 16), new Position(52, 16)));
        platforms.add(new Platform(new Position(15, 13), new Position(55, 13)));
        platforms.add(new Platform(new Position(20, 10), new Position(39, 10)));
        platforms.add(new Platform(new Position(27, 7), new Position(50, 7)));
        platforms.add(new Platform(new Position(30, 20), new Position(40, 20)));
        platforms.add(new Platform(new Position(48, 21), new Position(73, 21)));
        platforms.add(new Platform(new Position(4, 4), new Position(22, 4)));

        return platforms;
    }

    @Override
    public List<Ladder> createLadders() {
        List<Ladder> ladders = new ArrayList<>();

        ladders.add(new Ladder(new Position(33, 20), new Position(33, 23)));
        ladders.add(new Ladder(new Position(37, 16), new Position(37, 19)));
        ladders.add(new Ladder(new Position(49, 16), new Position(49, 20)));
        ladders.add(new Ladder(new Position(24, 13), new Position(24, 15)));
        ladders.add(new Ladder(new Position(48, 7), new Position(48, 12)));
        ladders.add(new Ladder(new Position(21, 4), new Position(21, 9)));
        ladders.add(new Ladder(new Position(34, 7), new Position(34, 9)));

        return ladders;
    }

    @Override
    public List<Coin> createCoins() {
        List<Coin> coins = new ArrayList<>();

        // to do

        return coins;
    }

    @Override
    public Key createKey() {
        return new Key(7, 3);
    }

    @Override
    public Door createDoor() {
        return new Door(73, 20);
    }

    @Override
    public List<Monster> createMonsters() {
        List<Monster> monsters = new ArrayList<>();

        monsters.add(new Boss(20, 15, createPlatforms().get(0)));

        return monsters;
    }
}
