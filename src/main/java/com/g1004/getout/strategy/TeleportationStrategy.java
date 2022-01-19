package com.g1004.getout.strategy;

import com.g1004.getout.Position;
import com.g1004.getout.element.monster.Monster;
import com.g1004.getout.strategy.MoveStrategy;

import java.util.Random;

/**
 * Teleportation Strategy class.
 * Each monster moves four steps forward and then teleports itself to another random position of the platform.
 */
public class TeleportationStrategy implements MoveStrategy {
    /**
     * Moves monster four steps forward and then changes its position to another random position of the platform he is in.
     * @param monster
     */
    @Override
    public void moveMonster(Monster monster) {
        int x = monster.getPosition().getX(), y = monster.getPosition().getY();
        int l = monster.getPlatform().getLeft().getX(), r = monster.getPlatform().getRight().getX();

        if (monster.getSteps() == 4) {
            Random random = new Random();
            int s = r - l + 1;
            int n = random.nextInt(s);

            monster.setPosition(new Position(l + n, y));
            monster.reset();
        }
        else {
            if (x == r)
                monster.setPosition(new Position(l, y));
            else
                monster.setPosition(new Position(x + 1, y));

            monster.step();
        }
    }
}
