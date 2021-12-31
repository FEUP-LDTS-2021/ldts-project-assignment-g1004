public class ConfusedStrategy implements MoveStrategy {
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
