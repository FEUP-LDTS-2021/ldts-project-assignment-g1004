package com.g1004.getout.element.monster;

import com.g1004.getout.strategy.MoveStrategy;
import com.g1004.getout.Platform;
import com.g1004.getout.strategy.TeleportationStrategy;

/**
 * com.g1004.getout.element.monster.Ghost monster class.
 * This monster is like a ghost, adopts a strategy in which he disappears and changes its position in the platform to a random one.
 */
public class Ghost extends Monster {
    /**
     * Constructor.
     * @param x coordinate.
     * @param y coordinate.
     * @param p platform.
     */
    public Ghost(int x, int y, Platform p) {
        super(x, y, "h", "#A9A9A9", 4, p);
    }

    /**
     * Defines strategy for ghost moves, according to its special features.
     * @return ghost strategy.
     */
    @Override
    protected MoveStrategy createMoveStrategy() {
        return new TeleportationStrategy();
    }

    /**
     * Comparison between position of the ghost and position of an object.
     * @param o
     * @return true if the position of both objects is the same.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;

        return (this == o || this.getPosition().equals(((Ghost) o).getPosition()));
    }
}