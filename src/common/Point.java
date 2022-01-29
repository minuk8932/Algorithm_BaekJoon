package common;

public class Point {
    private int row;
    private int col;
    private int cost;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Point(int row, int col, int cost) {
        this.row = row;
        this.col = col;
        this.cost = cost;
    }

    public static Point pointWithCost(int row, int col, int cost) {
        return new Point(row, col, cost);
    }

    public int getCost() {
        return cost;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}
