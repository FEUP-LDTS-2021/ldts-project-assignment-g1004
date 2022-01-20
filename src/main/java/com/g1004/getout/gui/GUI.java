package com.g1004.getout.gui;

import com.g1004.getout.element.*;
import com.g1004.getout.element.monster.Monster;
import com.g1004.getout.structures.Ladder;
import com.g1004.getout.structures.Platform;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.List;

public interface GUI {
    Screen getScreen();
    int getWidth();
    int getHeight();

    void drawMenu();
    void drawLevels(int n, List<Integer> scores);
    void drawInstructions();

    void drawBackground(String colourCode);
    void drawWalls(List<Wall> walls);
    void drawPlatform(Platform platform);
    void drawLadder(Ladder ladder);
    void drawCoin(Coin coin);
    void drawHero(Hero hero, boolean ladder);
    void drawMonster(Monster monster, boolean ladder);
    void drawMonster(Monster monster, boolean ladder, int index);
    void drawKey(Key key);
    void drawDoor(Door door);
    void drawHealthBar(Hero hero);
    void drawScore(int score);

    void drawVictory();
    void drawDefeat();

    void setBGColour(String colour);
    void clear();
    void refresh() throws IOException;
    void close() throws IOException;

    KeyStroke keyPress() throws IOException;
}