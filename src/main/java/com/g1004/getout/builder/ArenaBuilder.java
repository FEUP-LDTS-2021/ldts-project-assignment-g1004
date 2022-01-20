package com.g1004.getout.builder;

import com.g1004.getout.Ladder;
import com.g1004.getout.Platform;
import com.g1004.getout.element.*;
import com.g1004.getout.element.monster.Monster;

import java.util.List;

public interface ArenaBuilder {
    String createBackground();
    List<Platform> createPlatforms();
    List<Ladder> createLadders();
    List<Coin> createCoins();
    Key createKey();
    Door createDoor();
    List<Monster> createMonsters();
}
