package com.g1004.getout.builder;

import com.g1004.getout.Ladder;
import com.g1004.getout.Platform;
import com.g1004.getout.builder.ArenaBuilder;
import com.g1004.getout.element.Coin;
import com.g1004.getout.element.Door;
import com.g1004.getout.element.Key;
import com.g1004.getout.element.monster.Monster;

import java.util.ArrayList;
import java.util.List;

public class Apocalypse implements ArenaBuilder {
    @Override
    public String createBackground() {
        // to do
        return "";
    }

    @Override
    public List<Platform> createPlatforms() {
        List<Platform> platforms = new ArrayList<>();

        // to do

        return platforms;
    }

    @Override
    public List<Ladder> createLadders() {
        List<Ladder> ladders = new ArrayList<>();

        // to do

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
        // to do
        return new Key(0, 0);
    }

    @Override
    public Door createDoor() {
        // to do
        return new Door(0, 0);
    }

    @Override
    public List<Monster> createMonsters() {
        List<Monster> monsters = new ArrayList<>();

        // to do

        return monsters;
    }
}
