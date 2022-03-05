package common;

public class Coordinate<T> {
    private final T x;
    private final T y;
    private final T z;
    private final T r;

    public static class Builder<T> {
        // must
        private final T x;
        private final T y;

        // opt
        private T z;
        private T r;

        public Builder(T x, T y) {
            this.x = x;
            this.y = y;
        }

        public Builder z(T value) {
            z = value;
            return this;
        }

        public Builder r(T value) {
            r = value;
            return this;
        }

        public Coordinate build() {
            return new Coordinate(this);
        }
    }

    private Coordinate(Builder<T> builder) {
        x = builder.x;
        y = builder.y;
        z = builder.z;
        r = builder.r;
    }

    public T getX() {
        return x;
    }

    public T getY() {
        return y;
    }

    public T getZ() {
        return z;
    }

    public T getR() {
        return r;
    }
}
