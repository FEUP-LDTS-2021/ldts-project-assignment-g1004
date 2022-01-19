package com.g1004.getout.state;

import com.g1004.getout.Game;
import com.g1004.getout.gui.GUI;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

import static java.lang.System.exit;

public class MenuState extends GameState {
    public MenuState(Game game, GUI gui) {
        super(game, gui);
    }

    @Override
    public void display() throws IOException {
        gui.clear();
        gui.drawMenu();
        gui.refresh();
    }

    @Override
    public void readKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.Character) {
            switch (key.getCharacter()) {
                case '1':
                    game.changeState(new LevelsState(game, gui));
                    break;
                case '2':
                    game.changeState(new InstructionsState(game, gui));
                    break;
                case '0':
                    exit(0);
            }
        }
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
