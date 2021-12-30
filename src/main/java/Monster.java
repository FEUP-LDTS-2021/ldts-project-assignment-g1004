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

    public abstract Position move();
}
