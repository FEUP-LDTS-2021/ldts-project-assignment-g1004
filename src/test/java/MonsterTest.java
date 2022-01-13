import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MonsterTest {
    private Platform platform;

    @BeforeEach
    public void setup() {
        platform = new Platform(new Position(1, 5), new Position(8, 5));
    }

    @Test
    public void goblinTest() {
        Goblin goblin = new Goblin(6, 4, platform);

        MoveStrategy g = goblin.getMovement();
        Assertions.assertTrue(g instanceof RegularStrategy);
        goblin.checkDirection();
        Assertions.assertTrue(goblin.movingForward());

        goblin.move();
        goblin.move();
        goblin.move();
        Assertions.assertFalse(goblin.movingForward());
        Assertions.assertEquals(2, goblin.attack());
    }

    @Test
    public void zombieTest() {
        Zombie zombie = new Zombie(3, 4, platform);

        MoveStrategy z = zombie.getMovement();
        Assertions.assertTrue(z instanceof ConfusedStrategy);
        zombie.step();
        Assertions.assertEquals(1, zombie.getSteps());
        zombie.reset();
        Assertions.assertEquals(0, zombie.getSteps());

        zombie.move();
        zombie.move();
        zombie.move();
        Position p = new Position(4, 4);
        Assertions.assertEquals(p, zombie.getPosition());
        Assertions.assertTrue(zombie.movingForward());
        Assertions.assertEquals(3, zombie.attack());
    }

    @Test
    public void ghostTest() {
        Ghost ghost = new Ghost(4, 4, platform);

        MoveStrategy h = ghost.getMovement();
        Assertions.assertTrue(h instanceof TeleportationStrategy);

        ghost.move();
        ghost.move();
        ghost.move();
        ghost.move();
        ghost.move();
        Assertions.assertEquals(0, ghost.getSteps());
        Assertions.assertEquals(4, ghost.attack());
    }
}
