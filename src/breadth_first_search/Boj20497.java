package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author exponential-e
 * 백준 20497번: Bessie's Revolution
 *
 * @see https://www.acmicpc.net/problem/20497
 *
 */
public class Boj20497 {

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static final char EMPTY = '.';

    private static int N;
    private static ArrayList<Point> hide = new ArrayList<>();

    private static class Point{
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

        int[][] map = new int[N][N];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();

            for(int j = 0; j < N; j++) {
                char input = line.charAt(j);

                if(input != EMPTY) map[i][j] = 1;
                else hide.add(new Point(i, j));
            }
        }

        System.out.println(search(map));
    }

    private static int search(int[][] map) {
        int count = 0;

        int comp = bfs(map, new Point(-1, -1), 0);      // find origin section

        for(Point p: hide) {                                            // section is increased?
            if(bfs(map, p, comp) > 0) count++;
        }

        return count;
    }

    private static int bfs(int[][] map, Point block, int origin) {
        boolean[][] visit = new boolean[N][N];
        int size = 0;

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                if(map[row][col] == 1) continue;
                if(visit[row][col] || (row == block.row && col == block.col)) continue;
                Queue<Point> q = new LinkedList<>();
                q.offer(new Point(row, col));

                visit[row][col] = true;
                size++;

                while(!q.isEmpty()) {
                    Point current = q.poll();

                    for(final int[] DIRECTION: DIRECTIONS) {
                        int nextRow = current.row + DIRECTION[ROW];
                        int nextCol = current.col + DIRECTION[COL];

                        if(outOfRange(nextRow, nextCol)) continue;
                        if(map[nextRow][nextCol] == 1) continue;
                        if(visit[nextRow][nextCol] || (nextRow == block.row && nextCol == block.col)) continue;
                        visit[nextRow][nextCol] = true;

                        q.offer(new Point(nextRow, nextCol));
                    }
                }
            }
        }

        return size - origin;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || col < 0 || row >= N || col >= N;
    }
}
