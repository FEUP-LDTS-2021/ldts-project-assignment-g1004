package com.g1004.getout.MVC;

import com.g1004.getout.structures.Ladder;
import com.g1004.getout.structures.Platform;
import com.g1004.getout.element.Coin;
import com.g1004.getout.element.Door;
import com.g1004.getout.element.Hero;
import com.g1004.getout.element.Key;
import com.g1004.getout.element.monster.Monster;
import com.g1004.getout.gui.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ArenaViewerTest {
    private GUI gui;
    private Arena arena;
    private ArenaViewer viewer;

    @BeforeEach
    public void setup() {
        gui = Mockito.mock(GUI.class);
        arena = new Arena(1);
        viewer = new ArenaViewer(arena, gui);
    }

    @Test
    public void draw() {
        viewer.draw();

        Mockito.verify(gui, Mockito.times(1)).drawBackground(Mockito.anyString());
        Mockito.verify(gui, Mockito.times(1)).drawWalls(Mockito.anyList());
        Mockito.verify(gui, Mockito.atLeastOnce()).drawPlatform(Mockito.any(Platform.class));
        Mockito.verify(gui, Mockito.atLeastOnce()).drawLadder(Mockito.any(Ladder.class));
        Mockito.verify(gui, Mockito.atLeastOnce()).setBGColour(Mockito.anyString());
        Mockito.verify(gui, Mockito.atMostOnce()).drawKey(Mockito.any(Key.class));
        Mockito.verify(gui, Mockito.times(1)).drawDoor(Mockito.any(Door.class));
        Mockito.verify(gui, Mockito.atLeastOnce()).drawCoin(Mockito.any(Coin.class));
        Mockito.verify(gui, Mockito.times(1)).drawHero(Mockito.any(Hero.class), Mockito.anyBoolean());
        Mockito.verify(gui, Mockito.atLeastOnce()).drawMonster(Mockito.any(Monster.class), Mockito.anyBoolean());
        Mockito.verify(gui, Mockito.times(1)).drawHealthBar(Mockito.any(Hero.class));
        Mockito.verify(gui, Mockito.times(1)).drawScore(Mockito.anyInt());
    }

    @Test
    public void win() {
        viewer.win();

        Mockito.verify(gui, Mockito.times(1)).drawVictory();
    }

    @Test
    public void loss() {
        viewer.loss();

        Mockito.verify(gui, Mockito.times(1)).drawDefeat();
    }
}
