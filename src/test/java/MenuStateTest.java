import com.googlecode.lanterna.input.KeyStroke;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class MenuStateTest {
    @Test
    public void display() throws IOException {
        Game game1 = Mockito.mock(Game.class);
        GUI gui1 = Mockito.mock(GUI.class);
        GameState state1 = new MenuState(game1, gui1);

        state1.display();

        Mockito.verify(gui1, Mockito.times(1)).clear();
        Mockito.verify(gui1, Mockito.times(1)).drawMenu();
        Mockito.verify(gui1, Mockito.times(1)).refresh();
    }

    @Test
    public void readKey() {
        Game game2 = Mockito.mock(Game.class);
        GUI gui2 = Mockito.mock(GUI.class);
        GameState state2 = new MenuState(game2, gui2);

        state2.readKey(new KeyStroke('1', false, false));
        Mockito.verify(game2, Mockito.times(1)).changeState(Mockito.any(LevelsState.class));

        state2.readKey(new KeyStroke('2', false, false));
        Mockito.verify(game2, Mockito.times(1)).changeState(Mockito.any(InstructionsState.class));
    }

    @Test
    public void process() throws IOException {
        Game game3 = Mockito.mock(Game.class);
        GUI gui3 = Mockito.mock(GUI.class);
        GameState s = new MenuState(game3, gui3);
        GameState state3 = Mockito.spy(s);

        state3.process(10);

        Mockito.verify(state3, Mockito.times(1)).display();
        Mockito.verify(gui3, Mockito.times(1)).keyPress();
    }
}
