package common;

public class Point {
    private final int row;
    private final int col;
    private final int cost;

    public static class Builder {
        // must
        private final int row;
        private final int col;

        // opt
        private int cost;

        public Builder (int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Builder cost(int value) {
            cost = value;
            return this;
        }

        public Point build() {
            return new Point(this);
        }
    }

    private Point(Builder builder) {
        row = builder.row;
        col = builder.col;
        cost = builder.cost;
    }

    public static Point pointWithCost(int row, int col, int cost) {
        return new Point.Builder(row, col)
                .cost(cost)
                .build();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getCost() {
        return cost;
    }
}
