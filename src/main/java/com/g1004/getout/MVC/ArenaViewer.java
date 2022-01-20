package com.g1004.getout.MVC;

import com.g1004.getout.Ladder;
import com.g1004.getout.Platform;
import com.g1004.getout.Position;
import com.g1004.getout.element.Coin;
import com.g1004.getout.element.monster.Monster;
import com.g1004.getout.gui.GUI;

public class ArenaViewer {
    private final GUI gui;
    private final Arena arena;

    public ArenaViewer(Arena arena, GUI gui) {
        this.arena = arena;
        this.gui = gui;
    }

    public void draw() {
        gui.drawBackground(arena.background());
        gui.drawWalls(arena.walls());

        for (Platform platform : arena.platforms())
            gui.drawPlatform(platform);

        boolean h = false;
        for (Ladder ladder : arena.ladders()) {
            gui.drawLadder(ladder);

            if (ladder.hasElement(arena.hero().getPosition()))
                h = true;
        }

        gui.setBGColour(arena.background());

        if (!arena.hero().hasKey())
            gui.drawKey(arena.key());
        gui.drawDoor(arena.door());

        for (Coin coin : arena.coins())
            gui.drawCoin(coin);

        gui.drawHero(arena.hero(), h);
        gui.setBGColour(arena.background());

        for (Monster monster : arena.monsters()) {
            boolean m = false;

            if (monster.getSymbol().length() > 1) {
                int n = 0;
                for (Ladder l : arena.ladders()) {
                    for (int i = 0; i < monster.getSymbol().length(); i++) {
                        if (l.hasElement(new Position(monster.getPosition().getX() + i, monster.getPosition().getY()))) {
                            m = true;
                            n = i;
                            break;
                        }
                    }

                    if (m)
                        break;
                }

                gui.drawMonster(monster, m, n);
            }
            else {
                for (Ladder l : arena.ladders()) {
                    if (l.hasElement(monster.getPosition())) {
                        m = true;
                        break;
                    }
                }

                gui.drawMonster(monster, m);
            }

            gui.setBGColour(arena.background());
        }

        gui.drawHealthBar(arena.hero());
        gui.drawScore(arena.score());
    }

    public void win() {
        gui.drawVictory();
    }

    public void loss() {
        gui.drawDefeat();
    }
}
