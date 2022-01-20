package com.g1004.getout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class GameTest {
    private Game game;

    @BeforeEach
    public void setup() throws IOException {
        game = Game.getInstance(); // take into account that game is the same entity for all the tests
    }

    @Test
    public void passLevel() {
        game.passLevel();
        game.passLevel();

        Assertions.assertEquals(3, game.getProgress());
    }

    @Test
    public void checkSingleton() {
        game.passLevel();

        Assertions.assertEquals(4, game.getProgress());
        Assertions.assertFalse(game.finalLevel());
    }

    @Test
    public void scores() {
        Assertions.assertEquals(0, game.getScores().get(4));
        game.setScore(4, 5);
        Assertions.assertEquals(5, game.getScores().get(4));
        game.setScore(4, 8); // this score is higher
        Assertions.assertEquals(8, game.getScores().get(4));
        game.setScore(4, 7); // this score is lower
        Assertions.assertEquals(8, game.getScores().get(4));
    }
}
