package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiPredicate;

/**
 *
 * @author exponential-e
 * 백준 23973번: 표적지 옮기기
 *
 * @see https://www.acmicpc.net/problem/23973
 *
 */
public class Boj23973 {

    private static int N, M;
    private static List<Point23973> points = new ArrayList<>();
    private static int[][] target;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    private static final int ROW = 0, COL = 1;
    private static final int CIPHER = 10_000;
    private static final int SHOT = 1;

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        target = new int[N][M];
        for(int i = 0; i < N; i++) {
            String input = br.readLine();

            for(int j = 0; j < M; j++) {
                target[i][j] = input.charAt(j) - '0';
                if(target[i][j] != SHOT) continue;

                points.add(new Point23973(i, j));
            }
        }

        System.out.println(finder());
    }

    private static String finder() {
        for (Point23973 start: points) {
            if(!floodFill(start)) continue;

            return start.getRow() + " " + start.getCol();
        }

        return "-1";
    }

    private static boolean floodFill(Point23973 start) {
        Set<Integer> visited = new HashSet<>();

        Queue<Point23973> q = new ArrayDeque<>();
        q.offer(start);

        visited.add(start.getRow() * CIPHER + start.getCol());
        int count = 0;

        while(!q.isEmpty()) {
            int size = q.size();
            int oneCount = 0;
            count++;

            while(size-- > 0) {
                Point23973 current = q.poll();

                for (final int[] DIRECTION: DIRECTIONS) {
                    int nextRow = current.getRow() + DIRECTION[ROW];
                    int nextCol = current.getCol() + DIRECTION[COL];
                    int v = nextRow * CIPHER + nextCol;

                    if(OUT_OF_RANGE.test(nextRow, nextCol)) continue;
                    if(visited.contains(v)) continue;
                    visited.add(v);

                    oneCount += target[nextRow][nextCol];
                    q.offer(new Point23973(nextRow, nextCol));
                }
            }

            if(oneCount != 1) return false;
            if(count == 9) return true;
            if(count > 9) return false;
        }

        return false;
    }

    private static final BiPredicate<Integer, Integer> OUT_OF_RANGE = (row, col) ->
            row < 0 || row >= N || col < 0 || col >= M;
}

class Point23973 {
    private int row;
    private int col;

    public Point23973(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}