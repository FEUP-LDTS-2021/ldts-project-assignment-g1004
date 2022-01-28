package com.g1004.getout.element.monster;

import com.g1004.getout.strategy.MoveStrategy;
import com.g1004.getout.structures.Platform;
import com.g1004.getout.strategy.RegularStrategy;

public class Goblin extends Monster {
    public Goblin(int x, int y, Platform p) {
        super(x, y, "g", "#0E630D", 2, p);
    }

    @Override
    protected MoveStrategy createMoveStrategy() {
        return new RegularStrategy();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;

        return (this == o || this.getPosition().equals(((Goblin) o).getPosition()));
    }
}
