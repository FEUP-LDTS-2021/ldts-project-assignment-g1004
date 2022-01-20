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

public class TheShimmer implements ArenaBuilder {
    @Override
    public String createBackground() {
        return "#CBC3E3";
    }

    @Override
    public List<Platform> createPlatforms() {
        List<Platform> platforms = new ArrayList<>();

        platforms.add(new Platform(new Position(14, 21), new Position(20, 21)));
        platforms.add(new Platform(new Position(10, 19), new Position(26, 19)));
        platforms.add(new Platform(new Position(8, 16), new Position(14, 16)));
        platforms.add(new Platform(new Position(4, 13), new Position(25, 13)));
        platforms.add(new Platform(new Position(7, 10), new Position(11, 10)));
        platforms.add(new Platform(new Position(7, 7), new Position(18, 7)));
        platforms.add(new Platform(new Position(18, 9), new Position(30, 9)));
        platforms.add(new Platform(new Position(30, 7), new Position(44, 7)));
        platforms.add(new Platform(new Position(44, 9), new Position(56, 9)));
        platforms.add(new Platform(new Position(56, 7), new Position(68, 7)));
        platforms.add(new Platform(new Position(61, 10), new Position(69, 10)));
        platforms.add(new Platform(new Position(60, 16), new Position(65, 16)));
        platforms.add(new Platform(new Position(6, 5), new Position(7, 5)));
        platforms.add(new Platform(new Position(18, 5), new Position(19, 5)));
        platforms.add(new Platform(new Position(30, 5), new Position(31, 5)));
        platforms.add(new Platform(new Position(44, 5), new Position(45, 5)));
        platforms.add(new Platform(new Position(56, 5), new Position(57, 5)));
        platforms.add(new Platform(new Position(67, 5), new Position(68, 5)));


        return platforms;
    }

    @Override
    public List<Ladder> createLadders() {
        List<Ladder> ladders = new ArrayList<>();

        ladders.add(new Ladder(new Position(18, 21), new Position(18, 23)));
        ladders.add(new Ladder(new Position(15, 19), new Position(15, 20)));
        ladders.add(new Ladder(new Position(11, 16), new Position(11, 18)));
        ladders.add(new Ladder(new Position(14, 13), new Position(14, 15)));
        ladders.add(new Ladder(new Position(8, 10), new Position(8, 12)));
        ladders.add(new Ladder(new Position(11, 7), new Position(11, 9)));
        ladders.add(new Ladder(new Position(7, 5), new Position(7, 6)));
        ladders.add(new Ladder(new Position(18, 5), new Position(18, 8)));
        ladders.add(new Ladder(new Position(30, 5), new Position(30, 8)));
        ladders.add(new Ladder(new Position(44, 5), new Position(44, 8)));
        ladders.add(new Ladder(new Position(56, 5), new Position(56, 8)));
        ladders.add(new Ladder(new Position(67, 5), new Position(67, 6)));
        ladders.add(new Ladder(new Position(61, 7), new Position(61, 9)));
        ladders.add(new Ladder(new Position(64, 10), new Position(64, 15)));

        return ladders;
    }

    @Override
    public List<Coin> createCoins() {
        List<Coin> coins = new ArrayList<>();

        coins.add(new Coin(14, 20));
        coins.add(new Coin(20, 18));
        coins.add(new Coin(8, 15));
        coins.add(new Coin(17, 12));
        coins.add(new Coin(6, 4));
        coins.add(new Coin(19, 4));
        coins.add(new Coin(31, 4));
        coins.add(new Coin(45, 4));
        coins.add(new Coin(57, 4));
        coins.add(new Coin(68, 9));

        return coins;
    }

    @Override
    public Key createKey() {
        return new Key(65, 15);
    }

    @Override
    public Door createDoor() {
        return new Door(68, 4);
    }

    @Override
    public List<Monster> createMonsters() {
        List<Monster> monsters = new ArrayList<>();

        for (Platform p : createPlatforms()) {
            if (p.getLeft().getY() == 5)
                continue;

            if (p.getLeft().getY() == 7)
                monsters.add(new Ghost(p.getLeft().getX(), p.getLeft().getY() - 1, p));
            else if (p.getLeft().getY() % 2 == 0)
                monsters.add(new Zombie(p.getLeft().getX(), p.getLeft().getY() - 1, p));
            else
                monsters.add(new Goblin(p.getLeft().getX(), p.getLeft().getY() - 1, p));
        }

        return monsters;
    }
}
