package abstractions;

public abstract class Position {
    private int x;
    private int y;

    protected Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isPosition(Position position) {
        return this.x == position.x && this.y == position.y;
    }

    // TODO: move to a new manager?
    public double getDistance(Position position) {
        return Math.sqrt((this.x - position.x) * (this.x - position.x) + (this.y - position.y) * (this.y - position.y));
    }

    public abstract String getData();
}
