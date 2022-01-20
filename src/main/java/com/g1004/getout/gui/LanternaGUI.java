package com.g1004.getout.gui;

import com.g1004.getout.element.*;
import com.g1004.getout.element.monster.Monster;
import com.g1004.getout.structures.Ladder;
import com.g1004.getout.structures.Platform;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.List;

public class LanternaGUI implements GUI {
    private final int width;
    private final int height;
    private final Screen screen;
    private final TextGraphics graphics;

    public LanternaGUI(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        Terminal terminal = createTerminal();
        screen = createScreen(terminal);
        graphics = screen.newTextGraphics();
    }

    private Terminal createTerminal() throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();

        return terminal;
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        Screen screen;
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        return screen;
    }

    @Override
    public Screen getScreen() {
        return screen;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void drawMenu() {
        drawBackground("#58181F");

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(width/2 - 4, 6), "GET OUT!");
        graphics.putString(new TerminalPosition(width/2 - 4, 13), "[1] Play");
        graphics.putString(new TerminalPosition(width/2 - 8, 16), "[2] Instructions");
        graphics.putString(new TerminalPosition(width/2 - 4, 19), "[0] Exit");
    }

    @Override
    public void drawLevels(int n, List<Integer> scores) {
        drawBackground("#301934");

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(width/2 - 3, 3), "Levels");

        graphics.setForegroundColor(TextColor.Factory.fromString("#F5F5DC"));
        graphics.putString(new TerminalPosition(width/2 - 22, 5), "[ Highlighted level is where you currently are ]");

        for (int i = 0; i < 10; i++) {
            String x = "< " + (i + 1) + " > ";
            String s = "[" + scores.get(i) + "/10]";

            if (i + 1 <= n)
                graphics.setForegroundColor(TextColor.Factory.fromString("#FFA500"));

            switch (i) {
                case 0:
                    graphics.putString(new TerminalPosition(5, 9), x + " Goblin lair " + s);
                    break;
                case 1:
                    graphics.putString(new TerminalPosition(5, 11), x + " The undead " + s);
                    break;
                case 2:
                    graphics.putString(new TerminalPosition(5, 13), x + " Lost ruins " + s);
                    break;
                case 3:
                    graphics.putString(new TerminalPosition(5, 15), x + " Haunted mansion " + s);
                    break;
                case 4:
                    graphics.putString(new TerminalPosition(5, 17), x + " Necropolis " + s);
                    break;
                case 5:
                    graphics.putString(new TerminalPosition(42, 9), x + " Dark forest " + s);
                    break;
                case 6:
                    graphics.putString(new TerminalPosition(42, 11), x + " Dystopia " + s);
                    break;
                case 7:
                    graphics.putString(new TerminalPosition(42, 13), x + " The shimmer " + s);
                    break;
                case 8:
                    graphics.putString(new TerminalPosition(42, 15), x + " Apocalypse " + s);
                    break;
                case 9:
                    graphics.putString(new TerminalPosition(42, 17), x + " Last chance " + s);
            }

            graphics.setForegroundColor(TextColor.Factory.fromString("#F5F5DC"));
        }

        graphics.putString(new TerminalPosition(5, 20), "> [ENTER]: Play current level");
        graphics.putString(new TerminalPosition(5, 22), "> [digit]: Play a completed level again (type its number)");
        graphics.putString(new TerminalPosition(5, 24), "> [q]: Return to the main menu");

    }

    @Override
    public void drawInstructions() {
        drawBackground("#ADD8E6");

        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(new TerminalPosition(width/2 - 6, 2), "Instructions");

        graphics.setForegroundColor(TextColor.Factory.fromString("#654321"));

        graphics.putString(new TerminalPosition(2, 5), "> Welcome to GET OUT:");
        graphics.putString(new TerminalPosition(2, 6), "  Our superhero Nick Heya and the planet devourer Black Holer");
        graphics.putString(new TerminalPosition(2, 7), "  were both trapped in another dimension while fighting.");
        graphics.putString(new TerminalPosition(2, 8), "  Nick has no guns and needs your help to escape without");
        graphics.putString(new TerminalPosition(2, 9), "  being killed by any of the dangerous monsters that roam this");
        graphics.putString(new TerminalPosition(2, 10), "  place. Catch all the keys to open the doors that lead to his");
        graphics.putString(new TerminalPosition(2, 11), "  final battle with Holer and don't forget to collect some");
        graphics.putString(new TerminalPosition(2, 12), "  coins so Nick can buy an interdimensional trip back to Earth.");

        graphics.putString(new TerminalPosition(2, 14), "> Controls:");
        graphics.putString(new TerminalPosition(2, 15), "  [ArrowLeft]: move left");
        graphics.putString(new TerminalPosition(2, 16), "  [ArrowRight]: move right");
        graphics.putString(new TerminalPosition(2, 17), "  [ArrowUp]: move up");
        graphics.putString(new TerminalPosition(2, 18), "  [ArrowDown]: move down");
        graphics.putString(new TerminalPosition(2, 19), "  [q]: quit level");

        graphics.putString(new TerminalPosition(width/2 - 15, 22), "Press q to return to the main menu");
    }

    @Override
    public void drawBackground(String colourCode) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(colourCode));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
    }

    @Override
    public void drawWalls(List<Wall> walls) {
        for (Wall wall : walls) {
            graphics.setBackgroundColor(TextColor.Factory.fromString(wall.getColour()));
            graphics.putString(new TerminalPosition(wall.getPosition().getX(), wall.getPosition().getY()), wall.getSymbol());
        }
    }

    @Override
    public void drawPlatform(Platform platform) {
        for (Wall wall : platform.getWalls()) {
            graphics.setBackgroundColor(TextColor.Factory.fromString(wall.getColour()));
            graphics.putString(new TerminalPosition(wall.getPosition().getX(), wall.getPosition().getY()), wall.getSymbol());
        }
    }

    @Override
    public void drawLadder(Ladder ladder) {
        for (Bar bar : ladder.getBars()) {
            graphics.setBackgroundColor(TextColor.Factory.fromString(bar.getColour()));
            graphics.putString(new TerminalPosition(bar.getPosition().getX(), bar.getPosition().getY()), bar.getSymbol());
        }
    }

    @Override
    public void drawCoin(Coin coin) {
        graphics.setForegroundColor(TextColor.Factory.fromString(coin.getColour()));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(coin.getPosition().getX(), coin.getPosition().getY()), coin.getSymbol());
    }

    @Override
    public void drawKey(Key key) {
        graphics.setForegroundColor(TextColor.Factory.fromString(key.getColour()));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(key.getPosition().getX(), key.getPosition().getY()), key.getSymbol());
    }

    @Override
    public void drawDoor(Door door) {
        graphics.setForegroundColor(TextColor.Factory.fromString(door.getColour()));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(door.getPosition().getX(), door.getPosition().getY()), door.getSymbol());
    }

    @Override
    public void drawHero(Hero hero, boolean ladder) {
        if (ladder)
            graphics.setBackgroundColor(TextColor.Factory.fromString("#FFA500"));

        graphics.setForegroundColor(TextColor.Factory.fromString(hero.getColour()));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(hero.getPosition().getX(), hero.getPosition().getY()), hero.getSymbol());
    }

    @Override
    public void drawMonster(Monster monster, boolean ladder) {
        if (ladder)
            graphics.setBackgroundColor(TextColor.Factory.fromString("#FFA500"));

        graphics.setForegroundColor(TextColor.Factory.fromString(monster.getColour()));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(monster.getPosition().getX(), monster.getPosition().getY()), monster.getSymbol());
    }

    @Override
    public void drawMonster(Monster monster, boolean ladder, int index) {
        graphics.setForegroundColor(TextColor.Factory.fromString(monster.getColour()));
        graphics.enableModifiers(SGR.BOLD);

        if (monster.getSymbol().length() > 1) {
            for (int i = 0; i < monster.getSymbol().length(); i++) {
                if (ladder && i == index)
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#FFA500"));

                graphics.putString(new TerminalPosition(monster.getPosition().getX() + i, monster.getPosition().getY()), String.valueOf(monster.getSymbol().charAt(i)));
                graphics.setBackgroundColor(TextColor.Factory.fromString("#C99C99"));
            }
        }
    }

    @Override
    public void drawHealthBar(Hero hero) {
        String HP = "HP[" + Integer.toString(hero.getHP()) + "]";
        graphics.setBackgroundColor(TextColor.Factory.fromString("#964B00"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(new TerminalPosition(2, height - 2), HP);

        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        for (int c = 9; c <= 29; c++)
            graphics.putString(new TerminalPosition(c, height - 2), " ");

        for (int c = 9; c <= 9 + hero.getHP(); c++) {
            graphics.setBackgroundColor(TextColor.Factory.fromString("#00A814"));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(c, height - 2), " ");
        }
    }

    @Override
    public void drawScore(int score) {
        String s = "Score: " + Integer.toString(score);
        graphics.setBackgroundColor(TextColor.Factory.fromString("#964B00"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(new TerminalPosition(64, height - 2), s);
    }

    @Override
    public void drawVictory() {
        clear();

        drawBackground("#C7C72A");
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(new TerminalPosition(width/2 - 20, height/2), "You just escaped this level, congrats!!");

        try {
            refresh();
            Thread.sleep(2000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void drawDefeat() {
        clear();

        drawBackground("#0D455E");
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(width/2 - 9, height/2), "You died! Game over...");

        try {
            refresh();
            Thread.sleep(2000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setBGColour(String colour) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(colour));
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }

    @Override
    public KeyStroke keyPress() throws IOException {
        return screen.pollInput();
    }
}
