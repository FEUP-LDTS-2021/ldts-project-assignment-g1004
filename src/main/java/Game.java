import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Game class. This class sets the screen and the arena or level.
 */
public class Game {
    private final int width;
    private final int height;
    private GUI gui;
    private GameState state;
    private final int fps;
    private int progress;
    private List<Integer> scores;

    private static Game singleton = null;

    /**
     * Constructor.
     * It initializes arena and implements Lanterna methods, adding the screen to the arena.
     */
    private Game() throws IOException {
        width = 75;
        height = 27;
        gui = new LanternaGUI(width, height);
        state = new MenuState(this, gui);
        fps = 100;
        progress = 1;
        scores = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            scores.add(0);
    }

    public static Game getInstance() throws IOException {
        if (singleton == null)
            singleton = new Game();

        return singleton;
    }

    public void changeState(GameState state) {
        this.state = state;
    }

    public void passLevel() {
        if (progress < 10)
            progress++;
    }

    public boolean finalLevel() {
        return progress == 10;
    }

    public int getProgress() {
        return progress;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setScore(int level, Integer score) {
        if (score > scores.get(level))
            scores.set(level, score);
    }

    public void run() throws IOException {
        int frameTime = 1000 / fps;
        int delay = fps; // will allow us to control animations speed

        while (true) {
            if (delay == 0)
                delay = fps;

            long startTime = System.currentTimeMillis();

            state.process(delay);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            delay--;
        }
    }
}