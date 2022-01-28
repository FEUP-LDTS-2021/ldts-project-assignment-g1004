package com.g1004.getout;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Game game = Game.getInstance();
        game.run();
    }
}