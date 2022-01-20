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

public class DarkForest implements ArenaBuilder {
    @Override
    public String createBackground() {
        return "#90EE90";
    }

    @Override
    public List<Platform> createPlatforms() {
        List<Platform> platforms = new ArrayList<>();

        platforms.add(new Platform(new Position(15, 20), new Position(34, 20)));
        platforms.add(new Platform(new Position(47, 20), new Position(69, 20)));
        platforms.add(new Platform(new Position(4, 16), new Position(22, 16)));
        platforms.add(new Platform(new Position(25, 16), new Position(51, 16)));
        platforms.add(new Platform(new Position(29, 13), new Position(56, 13)));
        platforms.add(new Platform(new Position(44, 10), new Position(70, 10)));
        platforms.add(new Platform(new Position(4, 8), new Position(34, 8)));
        platforms.add(new Platform(new Position(49, 6), new Position(68, 6)));

        return platforms;
    }

    @Override
    public List<Ladder> createLadders() {
        List<Ladder> ladders = new ArrayList<>();

        ladders.add(new Ladder(new Position(23, 20), new Position(23, 23)));
        ladders.add(new Ladder(new Position(18, 16), new Position(18, 19)));
        ladders.add(new Ladder(new Position(29, 16), new Position(29, 19)));
        ladders.add(new Ladder(new Position(68, 20), new Position(68, 23)));
        ladders.add(new Ladder(new Position(50, 16), new Position(50, 19)));
        ladders.add(new Ladder(new Position(43, 13), new Position(43, 15)));
        ladders.add(new Ladder(new Position(32, 8), new Position(32, 12)));
        ladders.add(new Ladder(new Position(55, 10), new Position(55, 12)));
        ladders.add(new Ladder(new Position(49, 6), new Position(49, 9)));

        return ladders;
    }

    @Override
    public List<Coin> createCoins() {
        List<Coin> coins = new ArrayList<>();

        coins.add(new Coin(16, 19));
        coins.add(new Coin(33, 19));
        coins.add(new Coin(8, 15));
        coins.add(new Coin(16, 15));
        coins.add(new Coin(32, 15));
        coins.add(new Coin(39, 12));
        coins.add(new Coin(25, 7));
        coins.add(new Coin(54, 5));
        coins.add(new Coin(66, 9));
        coins.add(new Coin(64, 19));

        return coins;
    }

    @Override
    public Key createKey() {
        return new Key(6, 7);
    }

    @Override
    public Door createDoor() {
        return new Door(67, 5);
    }

    @Override
    public List<Monster> createMonsters() {
        List<Monster> monsters = new ArrayList<>();

        for (Platform p : createPlatforms()) {
            if (p.getLeft().getX() % 2 == 0)
                monsters.add(new Goblin(p.getLeft().getX(), p.getLeft().getY() - 1, p));
            else
                monsters.add(new Ghost(p.getLeft().getX(), p.getLeft().getY() - 1, p));
        }

        return monsters;
    }
}
