package com.g1004.getout.state;

import com.g1004.getout.Game;
import com.g1004.getout.gui.GUI;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

import static java.lang.System.exit;

public class LevelsState extends GameState {
    public LevelsState(Game game, GUI gui) {
        super(game, gui);
    }

    @Override
    public void display() throws IOException {
        gui.clear();
        gui.drawLevels(game.getProgress(), game.getScores());
        gui.refresh();
    }

    @Override
    public void readKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.Character) {
            char c = key.getCharacter();
            if (Character.isDigit(c) && c - 48 > 0 && c - 48 < game.getProgress())
                game.changeState(new PlayState(game, gui, c - 48));
            else if (c == 'q')
                game.changeState(new MenuState(game, gui));
        }
        else if (key.getKeyType() == KeyType.Enter)
            game.changeState(new PlayState(game, gui, game.getProgress()));
        else if (key.getKeyType() == KeyType.EOF)
            exit(0);
    }

    @Override
    public void process(int delay) throws IOException {
        display();

        KeyStroke key = gui.keyPress();
        if (key != null)
            readKey(key);
    }
}
