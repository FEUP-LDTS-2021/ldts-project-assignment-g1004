package com.g1004.getout.element.monster;

import com.g1004.getout.strategy.MoveStrategy;
import com.g1004.getout.structures.Platform;
import com.g1004.getout.strategy.RegularStrategy;

/**
 * com.g1004.getout.element.monster.Goblin monster class.
 * This monster adopts a strategy in which he moves forward to a corner of the platform and then reverses its direction, moving to the other corner.
 */
public class Goblin extends Monster {
    /**
     * Constructor.
     * @param x coordinate.
     * @param y coordinate.
     * @param p platform.
     */
    public Goblin(int x, int y, Platform p) {
        super(x, y, "g", "#0E630D", 2, p);
    }

    /**
     * Defines strategy for goblin moves, according to its special features.
     * @return goblin strategy.
     */
    @Override
    protected MoveStrategy createMoveStrategy() {
        return new RegularStrategy();
    }

    /**
     * Comparison between position of the goblin and position of an object.
     * @param o
     * @return true if the position of both objects is the same.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;

        return (this == o || this.getPosition().equals(((Goblin) o).getPosition()));
    }
}
