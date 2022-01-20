package com.g1004.getout.strategy;

import com.g1004.getout.position.Position;
import com.g1004.getout.element.monster.Monster;

/**
 * Regular Strategy class. Each monster moves to the left border and then to the right border of the platform.
 */
public class RegularStrategy implements MoveStrategy {
    /**
     * Moves monster to a corner of its platform and then reverses direction so that he moves towards the other corner.
     * @param monster
     */
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
