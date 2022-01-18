import java.io.IOException;

/**
 * Application class. This class contains the main method.
 */
public class Application {
    public static void main(String[] args) throws IOException {
        Game game = Game.getInstance();
        game.run();
    }
}