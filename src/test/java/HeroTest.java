import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeroTest {
    @Test
    public void move() {
        Hero h = new Hero(10, 10);

        Position p1 = new Position(10, 9);
        Assertions.assertEquals(p1, h.moveUp());

        Position p2 = new Position(10, 11);
        Assertions.assertEquals(p2, h.moveDown());

        Position p3 = new Position(9, 10);
        Assertions.assertEquals(p3, h.moveLeft());

        Position p4 = new Position(11, 10);
        Assertions.assertEquals(p4, h.moveRight());

    }

    @Test
    public void hurt() {
        Hero hero = new Hero(10, 10);

        Assertions.assertEquals(11, hero.getHP());
        hero.hurt(4);
        Assertions.assertEquals(7, hero.getHP());
        hero.hurt(3);
        hero.hurt(2);
        Assertions.assertFalse(hero.isDead());
        hero.hurt(4);
        Assertions.assertTrue(hero.isDead());
    }

    @Test
    public void key(){
        Hero hero = new Hero(10,10);

        Assertions.assertFalse(hero.hasKey());
        hero.catchKey();
        Assertions.assertTrue(hero.hasKey());
    }

}
