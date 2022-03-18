package common;

public class Square<T, E> {
    private final T x;
    private final T y;
    private final E width;
    private final E height;

    public static class Builder<T, E> {
        // must
        private final T x;
        private final T y;

        // opt
        private E width;
        private E height;

        public Builder(T x, T y) {
            this.x = x;
            this.y = y;
        }

        public Builder width(E value) {
            width = value;
            return this;
        }

        public Builder height(E value) {
            height = value;
            return this;
        }

        public Square build() {
            return new Square(this);
        }
    }

    private Square(Builder<T, E> builder) {
        x = builder.x;
        y = builder.y;
        width = builder.width;
        height = builder.height;
    }

    public T getX() {
        return x;
    }

    public T getY() {
        return y;
    }

    public E getWidth() {
        return width;
    }

    public E getHeight() {
        return height;
    }
}
