package com.g1004.getout.MVC.builder;

import com.g1004.getout.element.monster.Ghost;
import com.g1004.getout.element.monster.Goblin;
import com.g1004.getout.element.monster.Zombie;
import com.g1004.getout.position.Position;
import com.g1004.getout.structures.Ladder;
import com.g1004.getout.structures.Platform;
import com.g1004.getout.element.Coin;
import com.g1004.getout.element.Door;
import com.g1004.getout.element.Key;
import com.g1004.getout.element.monster.Monster;

import java.util.ArrayList;
import java.util.List;

public class Necropolis implements ArenaBuilder {
    @Override
    public String createBackground() {
        return "#5C609C";
    }

    @Override
    public List<Platform> createPlatforms() {
        List<Platform> platforms = new ArrayList<>();

        platforms.add(new Platform(new Position(35, 21), new Position(59, 21)));
        platforms.add(new Platform(new Position(24, 18), new Position(59, 18)));
        platforms.add(new Platform(new Position(10, 12), new Position(36, 12)));
        platforms.add(new Platform(new Position(39, 12), new Position(59, 12)));
        platforms.add(new Platform(new Position(26, 6), new Position(42, 6)));
        platforms.add(new Platform(new Position(50, 8), new Position(70, 8)));
        platforms.add(new Platform(new Position(68, 4), new Position(73, 4)));

        return platforms;
    }

    @Override
    public List<Ladder> createLadders() {
        List<Ladder> ladders = new ArrayList<>();

        ladders.add(new Ladder(new Position(43, 21), new Position(43, 23)));
        ladders.add(new Ladder(new Position(59, 18), new Position(59, 20)));
        ladders.add(new Ladder(new Position(43, 12), new Position(43, 17)));
        ladders.add(new Ladder(new Position(24, 12), new Position(24, 17)));
        ladders.add(new Ladder(new Position(32, 6), new Position(32, 11)));
        ladders.add(new Ladder(new Position(52, 8), new Position(52, 11)));
        ladders.add(new Ladder(new Position(69, 4), new Position(69, 7)));

        return ladders;
    }

    @Override
    public List<Coin> createCoins() {
        List<Coin> coins = new ArrayList<>();

        coins.add(new Coin(13, 11));
        coins.add(new Coin(19, 11));
        coins.add(new Coin(40, 20));
        coins.add(new Coin(54, 20));
        coins.add(new Coin(54, 17));
        coins.add(new Coin(39, 17));
        coins.add(new Coin(28, 17));
        coins.add(new Coin(42, 5));
        coins.add(new Coin(57, 7));
        coins.add(new Coin(67, 7));

        return coins;
    }

    @Override
    public Key createKey() {
        return new Key(28, 5);
    }

    @Override
    public Door createDoor() {
        return new Door(73, 3);
    }

    @Override
    public List<Monster> createMonsters() {
        List<Monster> monsters = new ArrayList<>();

        for (Platform p : createPlatforms()) {
            if (p.getLeft().getY() == 4)
                continue;

            if (p.getLeft().getY() % 4 == 0)
                monsters.add(new Zombie(p.getLeft().getX(), p.getLeft().getY() - 1, p));
            else
                monsters.add(new Ghost(p.getLeft().getX(), p.getLeft().getY() - 1, p));
        }

        return monsters;
    }
}
