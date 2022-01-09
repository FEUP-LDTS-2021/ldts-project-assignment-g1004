/**
 * Confused Strategy class.
 * Each monster takes two steps in the direction he is going and then one step back, and then restarts this process.
 */
public class ConfusedStrategy implements MoveStrategy {
    /**
     * Moves monster two steps forward and one step back, repeatedly.
     * @param monster
     */
    @Override
    public void moveMonster(Monster monster) {
        monster.checkDirection();

        int x = monster.getPosition().getX(), y = monster.getPosition().getY();
        if (monster.movingForward()) {
            if (monster.getSteps() == 2) {
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
