package com.g1004.getout.MVC.builder;

import com.g1004.getout.element.monster.Ghost;
import com.g1004.getout.element.monster.Goblin;
import com.g1004.getout.position.Position;
import com.g1004.getout.structures.Ladder;
import com.g1004.getout.structures.Platform;
import com.g1004.getout.element.Coin;
import com.g1004.getout.element.Door;
import com.g1004.getout.element.Key;
import com.g1004.getout.element.monster.Monster;

import java.util.ArrayList;
import java.util.List;

public class HauntedMansion implements ArenaBuilder {
    @Override
    public String createBackground() {
        return "#000000";
    }

    @Override
    public List<Platform> createPlatforms() {
        List<Platform> platforms = new ArrayList<>();

        platforms.add(new Platform(new Position(3, 21), new Position(25, 21)));
        platforms.add(new Platform(new Position(8, 18), new Position(29, 18)));
        platforms.add(new Platform(new Position(15, 13), new Position(36, 13)));
        platforms.add(new Platform(new Position(32, 16), new Position(42, 16)));
        platforms.add(new Platform(new Position(33, 9), new Position(69, 9)));
        platforms.add(new Platform(new Position(49, 6), new Position(53, 6)));
        platforms.add(new Platform(new Position(59, 14), new Position(69, 14)));
        platforms.add(new Platform(new Position(63, 18), new Position(70, 18)));

        return platforms;
    }

    @Override
    public List<Ladder> createLadders() {
        List<Ladder> ladders = new ArrayList<>();

        ladders.add(new Ladder(new Position(8, 21), new Position(8, 23)));
        ladders.add(new Ladder(new Position(14, 18), new Position(14, 20)));
        ladders.add(new Ladder(new Position(25, 13), new Position(25, 17)));
        ladders.add(new Ladder(new Position(34, 13), new Position(34, 15)));
        ladders.add(new Ladder(new Position(40, 9), new Position(40, 15)));
        ladders.add(new Ladder(new Position(52, 6), new Position(52, 8)));
        ladders.add(new Ladder(new Position(62, 9), new Position(62, 13)));
        ladders.add(new Ladder(new Position(65, 14), new Position(65, 17)));

        return ladders;
    }

    @Override
    public List<Coin> createCoins() {
        List<Coin> coins = new ArrayList<>();

        coins.add(new Coin(3, 20));
        coins.add(new Coin(23, 20));
        coins.add(new Coin(10, 17));
        coins.add(new Coin(28, 17));
        coins.add(new Coin(20, 12));
        coins.add(new Coin(47, 8));
        coins.add(new Coin(55, 8));
        coins.add(new Coin(65, 8));
        coins.add(new Coin(53, 5));
        coins.add(new Coin(59, 13));

        return coins;
    }

    @Override
    public Key createKey() {
        return new Key(50, 5);
    }

    @Override
    public Door createDoor() {
        return new Door(69, 17);
    }

    @Override
    public List<Monster> createMonsters() {
        List<Monster> monsters = new ArrayList<>();

        for (Platform p : createPlatforms()) {
            if (p.getLeft().getY() == 16 || p.getLeft().getY() == 6)
                continue;
            else if (p.getLeft().getX() == 63 && p.getLeft().getY() == 18)
                continue;
            else
                monsters.add(new Ghost(p.getLeft().getX(), p.getLeft().getY() - 1, p));
        }

        return monsters;
    }
}
