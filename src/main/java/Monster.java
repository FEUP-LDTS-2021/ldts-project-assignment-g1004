public abstract class Monster extends Element {
    protected Platform platform;
    protected boolean forward;
    protected int steps;

    public Monster(int x, int y, Platform p) {
        super(x, y);
        platform = p;
        forward = true;
        steps = 0;
    }

    public boolean movingForward() {
        return forward;
    }

    public int getSteps() {
        return steps;
    }

    public abstract void move();
}
