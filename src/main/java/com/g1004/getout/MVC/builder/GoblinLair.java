package com.g1004.getout.MVC.builder;

import com.g1004.getout.structures.Ladder;
import com.g1004.getout.structures.Platform;
import com.g1004.getout.position.Position;
import com.g1004.getout.element.Coin;
import com.g1004.getout.element.Door;
import com.g1004.getout.element.Key;
import com.g1004.getout.element.monster.Goblin;
import com.g1004.getout.element.monster.Monster;

import java.util.ArrayList;
import java.util.List;

public class GoblinLair implements ArenaBuilder {
    @Override
    public String createBackground() {
        return "#90EE90";
    }

    @Override
    public List<Platform> createPlatforms() {
        List<Platform> platforms = new ArrayList<>();

        platforms.add(new Platform(new Position(15, 20), new Position(55, 20)));
        platforms.add(new Platform(new Position(10, 16), new Position(30, 16)));
        platforms.add(new Platform(new Position(40, 16), new Position(60, 16)));
        platforms.add(new Platform(new Position(5, 12), new Position(20, 12)));
        platforms.add(new Platform(new Position(25, 12), new Position(47, 12)));
        platforms.add(new Platform(new Position(52, 12), new Position(65, 12)));
        platforms.add(new Platform(new Position(1, 8), new Position(32, 8)));
        platforms.add(new Platform(new Position(54, 8), new Position(73, 8)));

        return platforms;
    }

    @Override
    public List<Ladder> createLadders() {
        List<Ladder> ladders = new ArrayList<>();

        ladders.add(new Ladder(new Position(35, 20), new Position(35, 23)));
        ladders.add(new Ladder(new Position(20, 16), new Position(20, 19)));
        ladders.add(new Ladder(new Position(50, 16), new Position(50, 19)));
        ladders.add(new Ladder(new Position(15, 12), new Position(15, 15)));
        ladders.add(new Ladder(new Position(43, 12), new Position(43, 15)));
        ladders.add(new Ladder(new Position(55, 12), new Position(55, 15)));
        ladders.add(new Ladder(new Position(10, 8), new Position(10, 11)));
        ladders.add(new Ladder(new Position(29, 8), new Position(29, 11)));
        ladders.add(new Ladder(new Position(60, 8), new Position(60, 11)));

        return ladders;
    }

    @Override
    public List<Coin> createCoins() {
        List<Coin> coins = new ArrayList<>();

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

        return coins;
    }

    @Override
    public Key createKey() {
        return new Key(2, 7);
    }

    @Override
    public Door createDoor() {
        return new Door(73, 7);
    }

    @Override
    public List<Monster> createMonsters() {
        List<Monster> monsters = new ArrayList<>();

        for (Platform p : createPlatforms())
            monsters.add(new Goblin(p.getLeft().getX(), p.getLeft().getY() - 1, p));

        return monsters;
    }
}
