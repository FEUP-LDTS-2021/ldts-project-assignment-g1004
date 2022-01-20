package com.g1004.getout.element.monster;

import com.g1004.getout.position.Position;
import com.g1004.getout.strategy.MoveStrategy;
import com.g1004.getout.structures.Platform;
import com.g1004.getout.element.Element;

/**
 * com.g1004.getout.element.monster.Monster class. Manages and controls the monsters' movement on each platform.
 */
public abstract class Monster extends Element {
    private Platform platform;    /** platform of the monster */
    private boolean forward;            /** boolean that checks if the monster moves forward */
    private int steps;                  /** declares the monster steps */
    private MoveStrategy movement;      /** type of strategy that describes monster movement */
    private final int damage;

    /**
     * Constructor.
     * @param x coordinate.
     * @param y coordinate.
     * @param p platform.
     */
    public Monster(int x, int y, String symbol, String colour, int damage, Platform p) {
        super(x, y, symbol, colour);
        this.damage = damage;
        platform = p;
        forward = true;
        steps = 0;
        movement = createMoveStrategy();
    }
    /**
     * Obtain platform where monster is.
     * @return platform.
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * Obtain boolean to check whether monster moves forward or not.
     * @return true if monster moves forward or false if it doesn't.
     */
    public boolean movingForward() {
        return forward;
    }

    /**
     * Obtain number of steps of the monster.
     * @return steps.
     */
    public int getSteps() {
        return steps;
    }

    /**
     * Obtain strategy adopted by the monster to move.
     * @return
     */
    public MoveStrategy getMovement() {
        return movement;
    }

    /**
     * Check if monster is moving forward or not.
     */
    public void checkDirection() {
        if (position.getX() == platform.getLeft().getX())
            forward = true;
        else if (position.getX() == platform.getRight().getX())
            forward = false;
    }

    /**
     * Increases number of steps.
     */
    public void step() {
        steps++;
    }

    /**
     * Resets the counting of the steps up to zero.
     */
    public void reset() {
        steps = 0;
    }

    /**
     * Update monster movement.
     */
    public void move() {
        movement.moveMonster(this);
    }

    /**
     * Defines the monster movement according to its special features.
     * @return monster movement type.
     */
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
