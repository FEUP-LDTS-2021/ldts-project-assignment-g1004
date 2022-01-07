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

    public MoveStrategy getMovement() {
        return movement;
    }

    public void checkDirection() {
        if (position.getX() == platform.getLeft().getX())
            forward = true;
        else if (position.getX() == platform.getRight().getX())
            forward = false;
    }

    public void step() {
        steps++;
    }

    public void reset() {
        steps = 0;
    }

    public void move() {
        movement.moveMonster(this);
    }

    protected abstract MoveStrategy createMoveStrategy();
}
