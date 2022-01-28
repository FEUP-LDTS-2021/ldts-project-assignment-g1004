package com.g1004.getout.element.monster;

import com.g1004.getout.position.Position;
import com.g1004.getout.strategy.MoveStrategy;
import com.g1004.getout.structures.Platform;
import com.g1004.getout.element.Element;

public abstract class Monster extends Element {
    private Platform platform;
    private boolean forward;
    private int steps;
    private MoveStrategy movement;
    private final int damage;

    public Monster(int x, int y, String symbol, String colour, int damage, Platform p) {
        super(x, y, symbol, colour);
        this.damage = damage;
        platform = p;
        forward = true;
        steps = 0;
        movement = createMoveStrategy();
    }

    public Platform getPlatform() {
        return platform;
    }

    public boolean movingForward() {
        return forward;
    }

    public int getSteps() {
        return steps;
    }

    public MoveStrategy getMovement() {
        return movement;
    }

    public void checkDirection() {
        if (position.getX() == platform.getLeft().getX())
            forward = true;
        else if (position.getX() == platform.getRight().getX())
            forward = false;
    }

    public void step() {
        steps++;
    }

    public void reset() {
        steps = 0;
    }

    public void move() {
        movement.moveMonster(this);
    }

    protected abstract MoveStrategy createMoveStrategy();

    public int attack() {
        return damage;
    }

    public void switchPlatform(Platform p) {
        if ((position.getX() >= p.getLeft().getX()) && (position.getX() + 9 <= p.getRight().getX()))
            setPosition(new Position(position.getX(), p.getLeft().getY() - 1));
        else
            setPosition(new Position(p.getLeft().getX(), p.getLeft().getY() - 1));

        platform = p;
    }
}
