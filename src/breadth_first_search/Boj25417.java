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
 * 백준 25417번: 고속의 숫자 탐색
 *
 * @see https://www.acmicpc.net/problem/25417
 *
 */
public class Boj25417 {

    private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
    private static final int ROW = 0, COL = 1;
    private static final int INF = 1_000_000;
    private static final Predicate<Point<Integer, Integer>> OUT_OF_RANGE = p ->
        p.getRow() < 0 || p.getRow() >= 5 || p.getCol() < 0 || p.getCol() >= 5;
    private static int[][] map;
    private static int[][][] visit;
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
        Point<Integer, Integer> start = new Point.Builder<Integer, Integer>(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))
            .build();

        System.out.println(bfs(start));
    }

    private static int bfs(Point<Integer, Integer> start) {
        Queue<Point<Integer, Integer>> q = new ArrayDeque<>();

        q.offer(new Point.Builder<Integer, Integer>(start.getRow(), start.getCol()).cost(0).build());
        q.offer(new Point.Builder<Integer, Integer>(start.getRow(), start.getCol()).cost(1).build());
        visit[0][start.getRow()][start.getCol()] = 1;
        visit[1][start.getRow()][start.getCol()] = 1;

        while (!q.isEmpty()) {
            Point<Integer, Integer> current = q.poll();

            for (int type = 0; type <= 1; type++) {
                for (final int[] DIRECTION : DIRECTIONS) {
                    final Point<Integer, Integer> DIR = new Point.Builder<Integer, Integer>(DIRECTION[ROW], DIRECTION[COL]).cost(type).build();
                    Point<Integer, Integer> next;

                    if (type == 0) {
                        next = push(current, current.getCost(), DIR);
                        if (next == null) continue;

                        q.offer(next);
                        continue;
                    }

                    next = hop(current, current.getCost(), DIR);
                    if(next == null) continue;

                    q.offer(next);
                }
            }
        }

        int answer = Math.min(visit[0][end.getRow()][end.getCol()], visit[1][end.getRow()][end.getCol()]);
        return answer == INF ? -1 : answer - 1;
    }

    private static Point<Integer, Integer> push(Point<Integer, Integer> current, int prev, final Point<Integer, Integer> DIRECTION) {
        Point<Integer, Integer> pivot = new Point.Builder<Integer, Integer>(current.getRow(), current.getCol()).cost(DIRECTION.getCost()).build();

        while (true) {
            if ((pivot.getRow() != current.getRow() || pivot.getCol() != current.getCol()) && map[pivot.getRow()][pivot.getCol()] == 7) break;

            Point<Integer, Integer> next = new Point.Builder<Integer, Integer>(
                pivot.getRow() + DIRECTION.getRow(), pivot.getCol() + DIRECTION.getCol()
            ).cost(DIRECTION.getCost()).build();

            if (OUT_OF_RANGE.test(next)) break;
            if (map[next.getRow()][next.getCol()] == -1) break;
            pivot = next;
        }

        if (visit[DIRECTION.getCost()][pivot.getRow()][pivot.getCol()] <= visit[prev][current.getRow()][current.getCol()] + 1) return null;
        visit[DIRECTION.getCost()][pivot.getRow()][pivot.getCol()] = visit[prev][current.getRow()][current.getCol()] + 1;

        return pivot;
    }

    private static Point<Integer, Integer> hop(Point<Integer, Integer> current, int prev, final Point<Integer, Integer> DIRECTION) {
        Point<Integer, Integer> next = new Point.Builder<Integer, Integer>(
            current.getRow() + DIRECTION.getRow(), current.getCol() + DIRECTION.getCol()
        ).cost(DIRECTION.getCost()).build();

        if (OUT_OF_RANGE.test(next)) return null;
        if (map[next.getRow()][next.getCol()] == -1) return null;
        if (visit[DIRECTION.getCost()][next.getRow()][next.getCol()] <= visit[prev][current.getRow()][current.getCol()] + 1) return null;
        visit[DIRECTION.getCost()][next.getRow()][next.getCol()] = visit[prev][current.getRow()][current.getCol()] + 1;

        return next;
    }

    private static void init() {
        visit = new int[2][5][5];
        for (int i = 0; i < 5; i++) {
            Arrays.fill(visit[0][i], INF);
            Arrays.fill(visit[1][i], INF);
        }
    }
}