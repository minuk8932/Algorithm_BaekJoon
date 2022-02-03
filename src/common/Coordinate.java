package common;

public class Coordinate {
    private final int x;
    private final int y;

    public static class Builder {
        // must
        private final int x;
        private final int y;

        // opt

        public Builder(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Coordinate build() {
            return new Coordinate(this);
        }
    }

    private Coordinate(Builder builder) {
        x = builder.x;
        y = builder.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
