import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MonsterTest {
    @Test
    public void move() {
        Platform platform = new Platform(new Position(1, 5), new Position(8, 5));
        Goblin goblin = new Goblin(6, 4, platform);
        Zombie zombie = new Zombie(3, 4, platform);
        Ghost ghost = new Ghost(4, 4, platform);

        goblin.move();
        goblin.move();
        goblin.move();
        Assertions.assertFalse(goblin.movingForward());

        zombie.move();
        zombie.move();
        zombie.move();
        Position p = new Position(4, 4);
        Assertions.assertEquals(p, zombie.getPosition());
        Assertions.assertTrue(zombie.movingForward());

        ghost.move();
        ghost.move();
        ghost.move();
        ghost.move();
        ghost.move();
        Assertions.assertEquals(0, ghost.getSteps());
    }
}
