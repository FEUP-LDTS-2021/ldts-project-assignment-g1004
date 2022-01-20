package com.g1004.getout.element.monster;

import com.g1004.getout.Platform;
import com.g1004.getout.strategy.MoveStrategy;
import com.g1004.getout.strategy.SpecialStrategy;

public class Boss extends Monster {
    public Boss(int x, int y, Platform p) {
        super(x, y, "^~{o,,o}~^", "#071669", 1, p);
    }

    @Override
    protected MoveStrategy createMoveStrategy() {
        return new SpecialStrategy();
    }
}