public abstract class Monster extends Element {
    protected Platform platform;

    public Monster(int x, int y, Platform p) {
        super(x, y);
        platform = p;
    }

    public abstract Position move();
}
