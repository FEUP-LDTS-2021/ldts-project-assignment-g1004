package com.g1004.getout.element.monster;

import com.g1004.getout.strategy.ConfusedStrategy;
import com.g1004.getout.strategy.MoveStrategy;
import com.g1004.getout.structures.Platform;

public class Zombie extends Monster {
    public Zombie(int x, int y, Platform p) {
        super(x, y,"Z", "#964B00", 3, p);
    }

    @Override
    protected MoveStrategy createMoveStrategy() {
        return new ConfusedStrategy();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;

        return (this == o || this.getPosition().equals(((Zombie) o).getPosition()));
    }
}