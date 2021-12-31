public abstract class Monster extends Element {
    private final Platform platform;
    private boolean forward;
    private int steps;
    private MoveStrategy movement;

    public Monster(int x, int y, Platform p) {
        super(x, y);
        platform = p;
        forward = true;
        steps = 0;
        movement = createMoveStrategy();
    }

    public Platform getPlatform() {
        return platform;
    }

    public boolean movingForward() {
        return forward;
    }

    public int getSteps() {
        return steps;
    }

    public void checkDirection() {
        // to do
    }

    public void step() {
        // to do
    }

    public void reset() {
        // to do
    }

    public void move() {
        // to do
    }

    protected abstract MoveStrategy createMoveStrategy();
}
