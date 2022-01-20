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

public class Apocalypse implements ArenaBuilder {
    @Override
    public String createBackground() {
        return "#33333D";
    }

    @Override
    public List<Platform> createPlatforms() {
        List<Platform> platforms = new ArrayList<>();

        platforms.add(new Platform(new Position(33, 19), new Position(54, 19)));
        platforms.add(new Platform(new Position(39, 16), new Position(63, 16)));
        platforms.add(new Platform(new Position(27, 12), new Position(53, 12)));
        platforms.add(new Platform(new Position(57, 11), new Position(68, 11)));
        platforms.add(new Platform(new Position(22, 8), new Position(61, 8)));
        platforms.add(new Platform(new Position(6, 9), new Position(19, 9)));
        platforms.add(new Platform(new Position(10, 5), new Position(44, 5)));

        return platforms;
    }

    @Override
    public List<Ladder> createLadders() {
        List<Ladder> ladders = new ArrayList<>();

        ladders.add(new Ladder(new Position(34, 19), new Position(34, 23)));
        ladders.add(new Ladder(new Position(40, 16), new Position(40, 18)));
        ladders.add(new Ladder(new Position(46, 12), new Position(46, 15)));
        ladders.add(new Ladder(new Position(60, 8), new Position(60, 10)));
        ladders.add(new Ladder(new Position(30, 8), new Position(30, 11)));
        ladders.add(new Ladder(new Position(23, 5), new Position(23, 7)));
        ladders.add(new Ladder(new Position(12, 5), new Position(12, 8)));

        return ladders;
    }

    @Override
    public List<Coin> createCoins() {
        List<Coin> coins = new ArrayList<>();

        coins.add(new Coin(27, 23));
        coins.add(new Coin(64, 23));
        coins.add(new Coin(46, 18));
        coins.add(new Coin(52, 18));
        coins.add(new Coin(62, 15));
        coins.add(new Coin(43, 11));
        coins.add(new Coin(56, 7));
        coins.add(new Coin(16, 8));
        coins.add(new Coin(16, 4));
        coins.add(new Coin(42, 4));

        return coins;
    }

    @Override
    public Key createKey() {
        return new Key(66, 10);
    }

    @Override
    public Door createDoor() {
        return new Door(7, 8);
    }

    @Override
    public List<Monster> createMonsters() {
        List<Monster> monsters = new ArrayList<>();

        for (Platform p : createPlatforms()) {
            if (p.getLeft().getY() == 8 || p.getLeft().getY() == 9 || p.getLeft().getY() == 19)
                monsters.add(new Ghost(p.getLeft().getX(), p.getLeft().getY() - 1, p));
            else if (p.getLeft().getY() % 2 == 0)
                monsters.add(new Goblin(p.getLeft().getX(), p.getLeft().getY() - 1, p));
            else
                monsters.add(new Zombie(p.getLeft().getX(), p.getLeft().getY() - 1, p));
        }

        return monsters;
    }
}
