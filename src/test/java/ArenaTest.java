import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.Test;
import org.mockito.Mockito;

public class ArenaTest {

    @Test
    public void drawArena() {
        Arena arena = Mockito.mock(Arena.class);
        TextGraphics screen = Mockito.mock(TextGraphics.class);
        arena.draw(screen);
        Wall wall = Mockito.mock(Wall.class);

        Mockito.verify(wall, Mockito.atLeast(1)).draw(screen);
    }
/*
    @Test
    public void processArenaKey() {
        Game game = new Game();
        Arena arena = Mockito.mock(Arena.class);
        Mockito.verify(arena, Mockito.times(1)).;
    }

    @Test
    public void moveHeroInArena() {
        Game game = new Game();
        Arena arena = Mockito.mock(Arena.class);
        Mockito.verify(arena, Mockito.times(1)).;
    }
*/
}
