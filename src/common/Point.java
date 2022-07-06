package common;

import java.util.Objects;

public class Point<T, E> {
    private final T row;
    private final T col;
    private final E cost;

    private Point(Builder<T, E> builder) {
        row = builder.row;
        col = builder.col;
        cost = builder.cost;
    }

    public T getRow() {
        return row;
    }

    public T getCol() {
        return col;
    }

    public E getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Point)) {
            return false;
        }
        Point<?, ?> point = (Point<?, ?>) o;
        return row.equals(point.row) && col.equals(point.col) && Objects.equals(cost,
            point.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, cost);
    }

    public static class Builder<T, E> {
        // must
        private final T row;
        private final T col;

        // opt
        private E cost;

        public Builder (T row, T col) {
            this.row = row;
            this.col = col;
        }

        public Builder<T, E> cost(E value) {
            cost = value;
            return this;
        }

        public Point<T, E> build() {
            return new Point<>(this);
        }
    }
}
