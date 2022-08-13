package breadth_first_search;

import common.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.Predicate;

/**
 *
 * @author exponential-e
 * 백준 25416번: 빠른 숫자 탐색
 *
 * @see https://www.acmicpc.net/problem/25416
 *
 */
public class Boj25416 {

    private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
    private static final int ROW = 0, COL = 1;
    private static final int INF = 1_000_000;
    private static final Predicate<Point<Integer, Integer>> OUT_OF_RANGE = p ->
        p.getRow() < 0 || p.getRow() >= 5 || p.getCol() < 0 || p.getCol() >= 5;
    private static int[][] map;
    private static int[][] visit;
    private static Point<Integer, Integer> end;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[5][5];

        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] != 1)
                    continue;
                end = new Point.Builder<Integer, Integer>(i, j).build();
            }
        }

        init();

        StringTokenizer st = new StringTokenizer(br.readLine());
        Point<Integer, Integer> start = new Point
            .Builder<Integer, Integer>(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))
            .build();

        System.out.println(bfs(start));
    }

    private static int bfs(Point<Integer, Integer> start) {
        Queue<Point<Integer, Integer>> q = new ArrayDeque<>();

        q.offer(start);
        visit[start.getRow()][start.getCol()] = 1;

        while (!q.isEmpty()) {
            Point<Integer, Integer> current = q.poll();

            for (final int[] DIRECTION : DIRECTIONS) {
                Point<Integer, Integer> next = new Point
                    .Builder<Integer, Integer>(current.getRow() + DIRECTION[ROW], current.getCol() + DIRECTION[COL])
                    .build();
                if(OUT_OF_RANGE.test(next)) continue;
                if (map[next.getRow()][next.getCol()] == -1) continue;
                if (visit[next.getRow()][next.getCol()] <= visit[current.getRow()][current.getCol()] + 1) continue;
                visit[next.getRow()][next.getCol()] = visit[current.getRow()][current.getCol()] + 1;

                q.offer(next);
            }
        }

        return visit[end.getRow()][end.getCol()] == INF ? -1 : visit[end.getRow()][end.getCol()] - 1;
    }

    private static void init() {
        visit = new int[5][5];
        for(int i = 0; i < 5; i++) {
            Arrays.fill(visit[i], INF);
        }
    }
}
