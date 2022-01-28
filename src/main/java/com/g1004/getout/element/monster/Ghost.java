package com.g1004.getout.element.monster;

import com.g1004.getout.strategy.MoveStrategy;
import com.g1004.getout.structures.Platform;
import com.g1004.getout.strategy.TeleportationStrategy;

public class Ghost extends Monster {
    public Ghost(int x, int y, Platform p) {
        super(x, y, "h", "#A9A9A9", 4, p);
    }

    @Override
    protected MoveStrategy createMoveStrategy() {
        return new TeleportationStrategy();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass())
            return false;

        return (this == o || this.getPosition().equals(((Ghost) o).getPosition()));
    }
}