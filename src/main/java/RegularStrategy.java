/**
 * Strategy in which monster moves to the left border and then to the right border of the platform.
 */
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
