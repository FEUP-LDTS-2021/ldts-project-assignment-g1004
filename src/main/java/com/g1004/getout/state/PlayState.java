package com.g1004.getout.state;

import com.g1004.getout.Arena;
import com.g1004.getout.Game;
import com.g1004.getout.gui.GUI;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

import static java.lang.System.exit;

public class PlayState extends GameState {
    private final int numLevel;
    private Arena arena;

    public PlayState(Game game, GUI gui, int numLevel) {
        super(game, gui);
        this.numLevel = numLevel;
        arena = new Arena(gui, numLevel);
    }

    @Override
    public void display() throws IOException {
        gui.clear();
        arena.draw();
        gui.refresh();
    }

    @Override
    public void readKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
            game.changeState(new MenuState(game, gui));
        else if (key.getKeyType() == KeyType.EOF)
            exit(0);
        else
            arena.processKey(key);
    }

    @Override
    public void process(int delay) throws IOException {
        display();

        KeyStroke key = gui.keyPress();
        if (key != null) {
            readKey(key);

            if (arena.leave()) {
                if (numLevel == game.getProgress())
                    game.passLevel();
                game.setScore(numLevel - 1, arena.getScore());
                game.changeState(new LevelsState(game, gui));
                return;
            }

            if (arena.verifyMonsterCollisions()) {
                game.changeState(new LevelsState(game, gui));
                return;
            }
        }

        if (delay % 10 == 0) {
            arena.moveMonsters();

            if (arena.verifyMonsterCollisions())
                game.changeState(new LevelsState(game, gui));

            if (numLevel == 10 && delay % 30 == 0)
                arena.changeBossSpot();
        }
    }
}
