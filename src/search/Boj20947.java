package search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author exponential-e
 * 백준 20947번: 습격받은 도시
 *
 * @see https://www.acmicpc.net/problem/20947
 *
 */
public class Boj20947 {

    private static ArrayList<Point> start = new ArrayList<>();

    private static final char BUILDING = 'O';
    private static final char EMPTY = '.';
    private static final char BOMB = 'B';
    private static final String NEW_LINE = "\n";

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static int[][] trace;
    private static boolean[][] result;
    private static int N;

    private static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        char[][] map = new char[N][N];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();

            for(int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == BUILDING) start.add(new Point(i, j));
            }
        }

        filler(map);
        System.out.println(mapMaker(map));
    }

    private static String mapMaker(char[][] m) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(result[i][j]) sb.append(BOMB);
                else sb.append(m[i][j]);
            }

            sb.append(NEW_LINE);
        }

        return sb.toString();
    }

    private static void filler(char[][] m) {
        trace = new int[N][N];
        result = new boolean[N][N];

        for(Point s: start) {
            for(final int[] DIRECTION: DIRECTIONS) {
                int nextRow = s.row + DIRECTION[ROW];
                int nextCol = s.col + DIRECTION[COL];

                while(!outOfRange(nextRow, nextCol) && m[nextRow][nextCol] == EMPTY) {
                    trace[nextRow][nextCol]++;

                    nextRow += DIRECTION[ROW];
                    nextCol += DIRECTION[COL];
                }
            }
        }

        ArrayList<Point> number = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(trace[i][j] != 0) continue;
                number.add(new Point(i, j));
            }
        }

        for(Point p: number) {
            if(m[p.row][p.col] != EMPTY) continue;
            result[p.row][p.col] = true;
        }
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }
}
