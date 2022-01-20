package com.g1004.getout.MVC.builder;

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

public class TheUndead implements ArenaBuilder {
    @Override
    public String createBackground() {
        return "#A9A9A9";
    }

    @Override
    public List<Platform> createPlatforms() {
        List<Platform> platforms = new ArrayList<>();

        platforms.add(new Platform(new Position(10, 20), new Position(36, 20)));
        platforms.add(new Platform(new Position(20, 14), new Position(35, 14)));
        platforms.add(new Platform(new Position(12, 10), new Position(32, 10)));
        platforms.add(new Platform(new Position(14, 6), new Position(31, 6)));
        platforms.add(new Platform(new Position(11, 3), new Position(19, 3)));
        platforms.add(new Platform(new Position(25, 3), new Position(60, 3)));
        platforms.add(new Platform(new Position(51, 6), new Position(68, 6)));
        platforms.add(new Platform(new Position(42, 9), new Position(56, 9)));
        platforms.add(new Platform(new Position(57, 12), new Position(73, 12)));
        platforms.add(new Platform(new Position(57, 18), new Position(73, 18)));

        return platforms;
    }

    @Override
    public List<Ladder> createLadders() {
        List<Ladder> ladders = new ArrayList<>();

        ladders.add(new Ladder(new Position(16, 20), new Position(16, 23)));
        ladders.add(new Ladder(new Position(30, 14), new Position(30, 19)));
        ladders.add(new Ladder(new Position(21, 10), new Position(21, 13)));
        ladders.add(new Ladder(new Position(15, 3), new Position(15, 9)));
        ladders.add(new Ladder(new Position(28, 3), new Position(28, 5)));
        ladders.add(new Ladder(new Position(45, 3), new Position(45, 8)));
        ladders.add(new Ladder(new Position(53, 6), new Position(53, 8)));
        ladders.add(new Ladder(new Position(66, 6), new Position(66, 11)));
        ladders.add(new Ladder(new Position(59, 12), new Position(59, 17)));

        return ladders;
    }

    @Override
    public List<Coin> createCoins() {
        List<Coin> coins = new ArrayList<>();

        coins.add(new Coin(12, 19));
        coins.add(new Coin(35, 13));
        coins.add(new Coin(31, 9));
        coins.add(new Coin(18, 2));
        coins.add(new Coin(37, 2));
        coins.add(new Coin(59, 2));
        coins.add(new Coin(57, 5));
        coins.add(new Coin(56, 8));
        coins.add(new Coin(72, 11));
        coins.add(new Coin(62, 17));

        return coins;
    }

    @Override
    public Key createKey() {
        return new Key(48, 2);
    }

    @Override
    public Door createDoor() {
        return new Door(73, 17);
    }

    @Override
    public List<Monster> createMonsters() {
        List<Monster> monsters = new ArrayList<>();

        for (Platform p : createPlatforms()) {
            if (p.getLeft().getX() == 57 && p.getLeft().getY() == 18)
                continue;

            monsters.add(new Zombie(p.getLeft().getX(), p.getLeft().getY() - 1, p));
        }

        return monsters;
    }
}
