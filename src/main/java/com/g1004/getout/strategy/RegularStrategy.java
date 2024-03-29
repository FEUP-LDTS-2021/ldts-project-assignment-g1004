package com.g1004.getout.strategy;

import com.g1004.getout.position.Position;
import com.g1004.getout.element.monster.Monster;

public class RegularStrategy implements MoveStrategy {
    @Override
    public void moveMonster(Monster monster) {
        monster.checkDirection();

        int x = monster.getPosition().getX(), y = monster.getPosition().getY();
        if (monster.movingForward())
            monster.setPosition(new Position(x + 1, y));
        else
            monster.setPosition(new Position(x - 1, y));
    }
}
