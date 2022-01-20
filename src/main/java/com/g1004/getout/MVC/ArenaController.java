package com.g1004.getout.MVC;

import com.g1004.getout.Ladder;
import com.g1004.getout.Platform;
import com.g1004.getout.Position;
import com.g1004.getout.element.Coin;
import com.g1004.getout.element.Wall;
import com.g1004.getout.element.monster.Boss;
import com.g1004.getout.element.monster.Monster;
import com.g1004.getout.gui.GUI;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.Random;

import static java.lang.System.exit;

public class ArenaController {
    private Arena arena;
    private ArenaViewer viewer;
    private char direction;

    public ArenaController(Arena arena, GUI gui) {
        this.arena = arena;
        this.viewer = new ArenaViewer(arena, gui);
        direction = '0';
    }

    public void drawArena() {
        viewer.draw();
    }

    /**
     * Given a user input from the keyboard, moves hero if it is possible, setting a direction for hero's movement.
     * This makes it easier when it comes to dealing with the user's movement permissions:
     * Vertical movement, 'v' -> arrows up and down.
     * Horizontal movement, 'h' -> arrows left and right.
     * In the case of other movements (keys) the hero will not move.
     * @param key pressed by the user.
     */
    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case EOF:
                exit(0);
            case ArrowUp:
                direction = 'v';
                moveHero(arena.hero().moveUp());
                break;
            case ArrowDown:
                direction = 'v';
                moveHero(arena.hero().moveDown());
                break;
            case ArrowLeft:
                direction = 'h';
                moveHero(arena.hero().moveLeft());
                break;
            case ArrowRight:
                direction = 'h';
                moveHero(arena.hero().moveRight());
                break;
            default:
                direction = '0';
        }
    }

    /**
     * Changes hero position, if hero has permission to make that move.
     * @param position is the place to where the user wants the hero to move.
     */
    public void moveHero(Position position) {
        if (canHeroMove(position)) {
            arena.hero().setPosition(position);
            retrieveCoins();
            retrieveKey();
        }
    }

    /**
     * Determines if the hero's movement is possible according to the place where he is and the direction he wants to go:
     * The hero must move inside area delimited with walls.
     * On ladders, the movement can only be vertical, 'v'. Also, the hero can get down to one of them.
     * On platforms or on the floor wall, the movement can only be horizontal, 'h'.
     * In the case of other movements the hero will not move.
     * @param pos to where hero wants to move to.
     * @return true if hero can move or false if hero can´t move.
     */
    private boolean canHeroMove(Position pos) {
        for (Wall wall : arena.walls())
            if (wall.getPosition().equals(pos))
                return false;

        if (direction == 'v') {
            for (Ladder ladder : arena.ladders())
                if (ladder.hasElement(pos) || ladder.getTop().getY() - 1 == pos.getY())
                    return true;
        }
        else if (direction == 'h') {
            for (Platform platform : arena.platforms())
                if (platform.hasElement(pos))
                    return true;

            if (pos.getY() == arena.height() - 4)
                return true;
        }

        return false;
    }

    /**
     * Moves all the monsters in arena.
     */
    public void moveMonsters() {
        for (Monster monster : arena.monsters())
            monster.move();
    }

    public void changeBossSpot() {
        Random random = new Random();
        int n = random.nextInt(4);

        arena.monsters().get(0).switchPlatform(arena.platforms().get(n));
    }

    /**
     * Checks if any of the monsters collided with hero, in which case he will die, showing user's score.
     * @return true if hero died, otherwise false.
     */
    public boolean verifyMonsterCollisions() {
        if (arena.monsters().get(0) instanceof Boss) {
            for (int i = 0; i < 13; i++) {
                Position p = new Position(arena.monsters().get(0).getPosition().getX() + i, arena.monsters().get(0).getPosition().getY());
                if (p.equals(arena.hero().getPosition())) {
                    arena.hero().hurt(arena.monsters().get(0).attack());
                    if (arena.hero().isDead()) {
                        viewer.loss();
                        return true;
                    }
                }
            }
        }
        else {
            for (Monster monster : arena.monsters()) {
                if (monster.getPosition().equals(arena.hero().getPosition())) {
                    arena.hero().hurt(monster.attack());
                    if (arena.hero().isDead()) {
                        viewer.loss();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Each coin is removed if its position equals hero´s position.
     * Also, provides the score of the hero .
     */
    private void retrieveCoins() {
        for (Coin coin : arena.coins()) {
            if (arena.hero().getPosition().equals(coin.getPosition())) {
                arena.coins().remove(coin);
                arena.setScore(arena.score() + 1);
                break;
            }
        }
    }

    private void retrieveKey() {
        if (arena.hero().getPosition().equals(arena.key().getPosition()))
            arena.hero().catchKey();
    }

    public boolean leave() {
        if (arena.hero().hasKey() && arena.hero().getPosition().equals(arena.door().getPosition())){
            viewer.win();
            return true;
        }
        return false;
    }

    public Integer checkScore() {
        return arena.score();
    }
}
