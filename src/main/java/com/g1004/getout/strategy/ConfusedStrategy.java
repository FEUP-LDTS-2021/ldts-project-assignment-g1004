package com.g1004.getout.strategy;

import com.g1004.getout.position.Position;
import com.g1004.getout.element.monster.Monster;

public class ConfusedStrategy implements MoveStrategy {
    @Override
    public void moveMonster(Monster monster) {
        monster.checkDirection();

        int x = monster.getPosition().getX(), y = monster.getPosition().getY();
        if (monster.movingForward()) {
            if (monster.getSteps() == 2) {
                if (x == monster.getPlatform().getLeft().getX()) // so it doesn't move off the platform
                    monster.setPosition(new Position(x + 1, y));
                else
                    monster.setPosition(new Position(x - 1, y));
                monster.reset();
            }
            else {
                monster.setPosition(new Position(x + 1, y));
                monster.step();
            }
        }
        else {
            if (monster.getSteps() == 2) {
                if (x == monster.getPlatform().getRight().getX()) // so it doesn't move off the platform
                    monster.setPosition(new Position(x - 1, y));
                else
                    monster.setPosition(new Position(x + 1, y));
                monster.reset();
            }
            else {
                monster.setPosition(new Position(x - 1, y));
                monster.step();
            }
        }
    }
}
