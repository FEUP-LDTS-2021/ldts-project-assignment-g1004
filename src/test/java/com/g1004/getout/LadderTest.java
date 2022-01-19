package com.g1004.getout;

import com.g1004.getout.element.Bar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class LadderTest {
    private Ladder ladder;

    @BeforeEach
    public void setup() {
        ladder = new Ladder(new Position(6, 1), new Position(6, 4));
    }

    @Test
    public void createBars() {
        List<Bar> expected = Arrays.asList(new Bar(6, 1), new Bar(6, 2), new Bar(6, 3), new Bar(6, 4));
        Assertions.assertEquals(expected, ladder.getBars());
    }

    @Test
    public void tips() {
        Assertions.assertEquals(new Position(6, 1), ladder.getTop());
        Assertions.assertEquals(new Position(6, 4), ladder.getBottom());
    }

    @Test
    public void hasElement() {
        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(6);
        Mockito.when(position.getY()).thenReturn(3);

        Assertions.assertTrue(ladder.hasElement(position));
    }
}