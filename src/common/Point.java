package common;

public class Point<T> {
    private final int row;
    private final int col;
    private final T cost;

    public static class Builder<T> {
        // must
        private final int row;
        private final int col;

        // opt
        private T cost;

        public Builder (int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Builder cost(T value) {
            cost = value;
            return this;
        }

        public Point build() {
            return new Point(this);
        }
    }

    private Point(Builder<T> builder) {
        row = builder.row;
        col = builder.col;
        cost = builder.cost;
    }

    public static Point pointWithCost(int row, int col, int cost) {
        return new Point.Builder<Integer>(row, col)
                .cost(cost)
                .build();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public T getCost() {
        return cost;
    }
}
