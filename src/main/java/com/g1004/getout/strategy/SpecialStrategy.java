package com.g1004.getout.strategy;

import com.g1004.getout.Position;
import com.g1004.getout.element.monster.Monster;

public class SpecialStrategy implements MoveStrategy {
    @Override
    public void moveMonster(Monster monster) {
        int x = monster.getPosition().getX(), y = monster.getPosition().getY();
        if (x + 12 >= monster.getPlatform().getRight().getX())
            monster.setPosition(new Position(monster.getPlatform().getLeft().getX(), y));
        else
            monster.setPosition(new Position(x + 3, y));
    }
}
