package common;

public class Coordinate<T, E> {
    private final T x;
    private final T y;
    private final T z;
    private final E r;

    public static class Builder<T, E> {
        // must
        private final T x;
        private final T y;

        // opt
        private T z;
        private E r;

        public Builder(T x, T y) {
            this.x = x;
            this.y = y;
        }

        public Builder z(T value) {
            z = value;
            return this;
        }

        public Builder r(E value) {
            r = value;
            return this;
        }

        public Coordinate build() {
            return new Coordinate(this);
        }
    }

    private Coordinate(Builder<T, E> builder) {
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

    public E getR() {
        return r;
    }
}
