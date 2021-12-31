import java.util.Random;

public class TeleportationStrategy implements MoveStrategy {
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
