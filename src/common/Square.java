package common;

public class Square<Coordinate, E> {
    private final Coordinate coordinate;
    private final E width;
    private final E height;

    public static class Builder<Coordinate, E> {
        // must
        private final Coordinate coordinate;

        // opt
        private E width;
        private E height;

        public Builder(Coordinate coordinate) {
            this.coordinate = coordinate;
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

    private Square(Builder<Coordinate, E> builder) {
        this.coordinate = builder.coordinate;
        width = builder.width;
        height = builder.height;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public E getWidth() {
        return width;
    }

    public E getHeight() {
        return height;
    }
}
