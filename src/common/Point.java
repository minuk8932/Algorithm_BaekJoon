package common;

public class Point {
    private int row;
    private int col;
    private int dir;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Point(int row, int col, int dir) {
        this.row = row;
        this.col = col;
        this.dir = dir;
    }

    public static Point pointWithDirection(int row, int col, int dir) {
        return new Point(row, col, dir);
    }

    public int getDir() {
        return dir;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}
