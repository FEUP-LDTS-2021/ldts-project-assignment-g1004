package com.g1004.getout.MVC.builder;

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

public class LostRuins implements ArenaBuilder {
    @Override
    public String createBackground() {
        return "#A9A9A9";
    }

    @Override
    public List<Platform> createPlatforms() {
        List<Platform> platforms = new ArrayList<>();

        platforms.add(new Platform(new Position(9, 20), new Position(19, 20)));
        platforms.add(new Platform(new Position(14, 17), new Position(29, 17)));
        platforms.add(new Platform(new Position(23, 20), new Position(33, 20)));
        platforms.add(new Platform(new Position(21, 14), new Position(34, 14)));
        platforms.add(new Platform(new Position(26, 11), new Position(51, 11)));
        platforms.add(new Platform(new Position(45, 14), new Position(56, 14)));
        platforms.add(new Platform(new Position(48, 17), new Position(59, 17)));
        platforms.add(new Platform(new Position(39, 20), new Position(50, 20)));
        platforms.add(new Platform(new Position(54, 20), new Position(69, 20)));
        platforms.add(new Platform(new Position(64, 18), new Position(73, 18)));
        platforms.add(new Platform(new Position(65, 15), new Position(73, 15)));
        platforms.add(new Platform(new Position(67, 12), new Position(73, 12)));
        platforms.add(new Platform(new Position(65, 9), new Position(73, 9)));
        platforms.add(new Platform(new Position(69, 6), new Position(73, 6)));

        return platforms;
    }

    @Override
    public List<Ladder> createLadders() {
        List<Ladder> ladders = new ArrayList<>();

        ladders.add(new Ladder(new Position(15, 20), new Position(15, 23)));
        ladders.add(new Ladder(new Position(18, 17), new Position(18, 19)));
        ladders.add(new Ladder(new Position(25, 14), new Position(25, 19)));
        ladders.add(new Ladder(new Position(30, 11), new Position(30, 13)));
        ladders.add(new Ladder(new Position(47, 11), new Position(47, 13)));
        ladders.add(new Ladder(new Position(53, 14), new Position(53, 16)));
        ladders.add(new Ladder(new Position(49, 17), new Position(49, 19)));
        ladders.add(new Ladder(new Position(58, 17), new Position(58, 19)));
        ladders.add(new Ladder(new Position(68, 18), new Position(68, 19)));
        ladders.add(new Ladder(new Position(66, 15), new Position(66, 17)));
        ladders.add(new Ladder(new Position(70, 12), new Position(70, 14)));
        ladders.add(new Ladder(new Position(68, 9), new Position(68, 11)));
        ladders.add(new Ladder(new Position(70, 6), new Position(70, 8)));

        return ladders;
    }

    @Override
    public List<Coin> createCoins() {
        List<Coin> coins = new ArrayList<>();

        coins.add(new Coin(19, 23));
        coins.add(new Coin(36, 23));
        coins.add(new Coin(28, 19));
        coins.add(new Coin(31, 19));
        coins.add(new Coin(35, 10));
        coins.add(new Coin(40, 10));
        coins.add(new Coin(44, 19));
        coins.add(new Coin(47, 19));
        coins.add(new Coin(50, 19));
        coins.add(new Coin(73, 14));

        return coins;
    }

    @Override
    public Key createKey() {
        return new Key(55, 19);
    }

    @Override
    public Door createDoor() {
        return new Door(73, 5);
    }

    @Override
    public List<Monster> createMonsters() {
        List<Monster> monsters = new ArrayList<>();

        for (Platform p : createPlatforms()) {
            if (p.getLeft().getY() == 6 || p.getLeft().getY() == 12)
                continue;

            if (p.getLeft().getY() % 2 == 0)
                monsters.add(new Goblin(p.getLeft().getX(), p.getLeft().getY() - 1, p));
            else
                monsters.add(new Zombie(p.getLeft().getX(), p.getLeft().getY() - 1, p));
        }

        return monsters;
    }
}
