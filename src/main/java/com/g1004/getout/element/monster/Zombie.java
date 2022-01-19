package com.g1004.getout.element.monster;

import com.g1004.getout.strategy.ConfusedStrategy;
import com.g1004.getout.strategy.MoveStrategy;
import com.g1004.getout.Platform;

/**
 * com.g1004.getout.element.monster.Zombie monster class.
 * This monster adopts a strategy in which he seems confused in the direction he is going, so he takes steps forward and back.
 */
public class Zombie extends Monster {
    /**
     * Constructor.
     * @param x coordinate.
     * @param y coordinate.
     * @param p platform.
     */
    public Zombie(int x, int y, Platform p) {
        super(x, y,"Z", "#964B00", 3, p);
    }

    /**
     * Defines strategy for zombie moves, according to its special features.
     * @return zombie strategy.
     */
    @Override
    protected MoveStrategy createMoveStrategy() {
        return new ConfusedStrategy();
    }

    /**
     * Comparison between position of the zombie and position of an object.
     * @param o
     * @return true if the position of both objects is the same.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;

        return (this == o || this.getPosition().equals(((Zombie) o).getPosition()));
    }
}