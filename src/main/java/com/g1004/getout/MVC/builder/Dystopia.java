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

public class Dystopia implements ArenaBuilder {
    @Override
    public String createBackground() {
        return "#CBC3E3";
    }

    @Override
    public List<Platform> createPlatforms() {
        List<Platform> platforms = new ArrayList<>();

        platforms.add(new Platform(new Position(13, 19), new Position(42, 19)));
        platforms.add(new Platform(new Position(10, 15), new Position(28, 15)));
        platforms.add(new Platform(new Position(36, 14), new Position(59, 14)));
        platforms.add(new Platform(new Position(14, 11), new Position(39, 11)));
        platforms.add(new Platform(new Position(1, 7), new Position(47, 7)));
        platforms.add(new Platform(new Position(53, 11), new Position(70, 11)));
        platforms.add(new Platform(new Position(64, 5), new Position(73, 5)));

        return platforms;
    }

    @Override
    public List<Ladder> createLadders() {
        List<Ladder> ladders = new ArrayList<>();

        ladders.add(new Ladder(new Position(35, 19), new Position(35, 23)));
        ladders.add(new Ladder(new Position(26, 15), new Position(26, 18)));
        ladders.add(new Ladder(new Position(18, 11), new Position(18, 14)));
        ladders.add(new Ladder(new Position(37, 11), new Position(37, 13)));
        ladders.add(new Ladder(new Position(54, 11), new Position(54, 13)));
        ladders.add(new Ladder(new Position(32, 7), new Position(32, 10)));
        ladders.add(new Ladder(new Position(68, 5), new Position(68, 10)));

        return ladders;
    }

    @Override
    public List<Coin> createCoins() {
        List<Coin> coins = new ArrayList<>();

        coins.add(new Coin(18, 23));
        coins.add(new Coin(36, 23));
        coins.add(new Coin(41, 18));
        coins.add(new Coin(16, 18));
        coins.add(new Coin(43, 13));
        coins.add(new Coin(13, 14));
        coins.add(new Coin(61, 10));
        coins.add(new Coin(44, 6));
        coins.add(new Coin(20, 6));
        coins.add(new Coin(49, 23));

        return coins;
    }

    @Override
    public Key createKey() {
        return new Key(6, 6);
    }

    @Override
    public Door createDoor() {
        return new Door(73, 4);
    }

    @Override
    public List<Monster> createMonsters() {
        List<Monster> monsters = new ArrayList<>();

        for (Platform p : createPlatforms()) {
            if (p.getLeft().getY() == 7 || p.getLeft().getY() == 14)
                monsters.add(new Ghost(p.getLeft().getX(), p.getLeft().getY() - 1, p));
            else if (p.getLeft().getY() == 19)
                monsters.add(new Zombie(p.getLeft().getX(), p.getLeft().getY() - 1, p));
            else
                monsters.add(new Goblin(p.getLeft().getX(), p.getLeft().getY() - 1, p));
        }

        return monsters;
    }
}
