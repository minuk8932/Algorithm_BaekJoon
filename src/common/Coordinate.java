package common;

public class Coordinate<T, E> {
    private final T x;
    private final T y;
    private final T z;
    private final E r;

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

        public Builder<T, E> z(T value) {
            z = value;
            return this;
        }

        public Builder<T, E> r(E value) {
            r = value;
            return this;
        }

        public Coordinate<T, E> build() {
            return new Coordinate<>(this);
        }
    }
}
