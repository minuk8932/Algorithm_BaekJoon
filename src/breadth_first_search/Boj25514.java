package breadth_first_search;

import common.Point;
import java.io.*;
import java.util.*;
import java.util.function.Predicate;

/**
 *
 * @author exponential-e
 * 백준 25514번: 고속의 오름차순 숫자 탐색
 *
 * @see https://www.acmicpc.net/problem/25514
 *
 */
public class Boj25514 {

    private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
    private static final int ROW = 0, COL = 1;
    private static final int NON_VISIT = 1_000_000_000;
    private static final Predicate<Point<Integer, Integer>> OUT_OF_RANGE = p ->
        p.getRow() < 0 || p.getRow() >= 5 || p.getCol() < 0 || p.getCol() >= 5;

    private static final int BREAK = 7;
    private static int[][] map;
    private static int[][][] visit;
    private static ArrayList<Point<Integer, Integer>> ends = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[5][5];

        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] < 1 || map[i][j] == BREAK) continue;
                ends.add(new Point.Builder<Integer, Integer>(i, j).cost(map[i][j]).build());
            }
        }

        ends.sort(Comparator.comparingInt(Point::getCost));

        StringTokenizer st = new StringTokenizer(br.readLine());
        Point<Integer, Integer> start = new Point.Builder<Integer, Integer>(
            Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())).cost(0).build();

        visit = new int[2][5][5];

        int answer = 0;
        for(int destination = 1; destination <= 6; destination++) {
            init();

            int cost = bfs(destination, start);
            start = ends.get(destination - 1);
            answer += cost;

            if(cost < NON_VISIT) continue;
            answer = NON_VISIT;
            break;
        }

        System.out.println(answer == NON_VISIT ? -1: answer);
    }

    private static int bfs(int destination, Point<Integer, Integer> start) {
        Queue<Point<Integer, Integer>> q = new ArrayDeque<>();

        q.offer(new Point.Builder<Integer, Integer>(start.getRow(), start.getCol()).cost(0).build());
        q.offer(new Point.Builder<Integer, Integer>(start.getRow(), start.getCol()).cost(1).build());
        visit[0][start.getRow()][start.getCol()] = 0;
        visit[1][start.getRow()][start.getCol()] = 0;

        while (!q.isEmpty()) {
            Point<Integer, Integer> current = q.poll();

            for (int type = 0; type <= 1; type++) {
                for (final int[] DIRECTION : DIRECTIONS) {
                    final Point<Integer, Integer> DIR = new Point.Builder<Integer, Integer>(
                        DIRECTION[ROW], DIRECTION[COL]).cost(type).build();

                    Point<Integer, Integer> next;

                    if (type == 0) {
                        next = push(current, current.getCost(), DIR);
                        if (next == null) continue;

                        if(map[next.getRow()][next.getCol()] == destination)
                            return visit[type][next.getRow()][next.getCol()];

                        q.offer(next);
                        continue;
                    }

                    next = hop(current, current.getCost(), DIR);
                    if(next == null) continue;

                    if(map[next.getRow()][next.getCol()] == destination)
                        return visit[type][next.getRow()][next.getCol()];

                    q.offer(next);
                }
            }
        }

        return NON_VISIT;
    }

    private static Point<Integer, Integer> push(
        Point<Integer, Integer> current, int prev, final Point<Integer, Integer> DIRECTION) {
        Point<Integer, Integer> pivot = new Point.Builder<Integer, Integer>(
            current.getRow(), current.getCol()).cost(DIRECTION.getCost()).build();

        while (true) {
            if ((pivot.getRow() != current.getRow() ||
                pivot.getCol() != current.getCol()) &&
                map[pivot.getRow()][pivot.getCol()] == BREAK) break;

            Point<Integer, Integer> next = new Point.Builder<Integer, Integer>(
                pivot.getRow() + DIRECTION.getRow(), pivot.getCol() + DIRECTION.getCol()
            ).cost(DIRECTION.getCost()).build();

            if (OUT_OF_RANGE.test(next)) break;
            if (map[next.getRow()][next.getCol()] == -1) break;
            pivot = next;
        }

        if (visit[DIRECTION.getCost()][pivot.getRow()][pivot.getCol()] <=
            visit[prev][current.getRow()][current.getCol()] + 1) return null;
        visit[DIRECTION.getCost()][pivot.getRow()][pivot.getCol()] =
            visit[prev][current.getRow()][current.getCol()] + 1;

        return pivot;
    }

    private static Point<Integer, Integer> hop(
        Point<Integer, Integer> current, int prev, final Point<Integer, Integer> DIRECTION) {
        Point<Integer, Integer> next = new Point.Builder<Integer, Integer>(
            current.getRow() + DIRECTION.getRow(), current.getCol() + DIRECTION.getCol()
        ).cost(DIRECTION.getCost()).build();

        if (OUT_OF_RANGE.test(next)) return null;
        if (map[next.getRow()][next.getCol()] == -1) return null;
        if (visit[DIRECTION.getCost()][next.getRow()][next.getCol()] <=
            visit[prev][current.getRow()][current.getCol()] + 1) return null;
        visit[DIRECTION.getCost()][next.getRow()][next.getCol()] =
            visit[prev][current.getRow()][current.getCol()] + 1;

        return next;
    }

    private static void init() {
        for (int i = 0; i < 5; i++) {
            Arrays.fill(visit[0][i], NON_VISIT);
            Arrays.fill(visit[1][i], NON_VISIT);
        }
    }
}
