package breadth_first_search;

import common.Point;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 *
 * @author exponential-e
 * 백준 24463번: 미로
 *
 * @see https://www.acmicpc.net/problem/24463
 *
 */
public class Boj24463 {

    private static boolean[][] track;
    private static int[][] visit;
    private static int N, M;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static final String NEW_LINE = "\n";
    private static final char BLOCK = '+';
    private static final char FILLER = '@';
    private static final char EMPTY = '.';

    private static Point<Integer, Integer> start;
    private static Point<Integer, Integer> end;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        visit = new int[N][M];
        track = new boolean[N][M];

        boolean flag = false;
        for(int i = 0; i < N; i++) {
            String line = br.readLine();

            for(int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);

                if(!EXIT_FINDER.test(new Point.Builder(i, j).build(), map[i][j])) continue;

                if(flag) end = new Point.Builder(i, j).build();
                else start = new Point.Builder(i, j).build();

                flag = true;
            }
        }

        bfs(map);
        reverse(map);
        System.out.println(tracking(map));
    }

    private static String tracking(char[][] map) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == BLOCK) sb.append(BLOCK);
                else sb.append(track[i][j] ? EMPTY: FILLER);
            }

            sb.append(NEW_LINE);
        }

        return sb.toString();
    }

    private static void reverse(char[][] map) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(end);

        track[end.getRow()][end.getCol()] = true;

        while(!q.isEmpty()) {
            Point<Integer, Integer> current = q.poll();

            for (final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.getRow() + DIRECTION[ROW];
                int nextCol = current.getCol() + DIRECTION[COL];

                Point<Integer, Integer> next = new Point.Builder(nextRow, nextCol).build();

                if(OUT_OF_RANGE.test(next)) continue;
                if(map[next.getRow()][next.getCol()] == BLOCK) continue;
                if(visit[current.getRow()][current.getCol()] - 1 != visit[next.getRow()][next.getCol()]) continue;
                track[next.getRow()][next.getCol()] = true;

                q.offer(next);
            }
        }
    }

    private static void bfs(char[][] map) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(start);

        visit[start.getRow()][start.getCol()] = 1;

        while(!q.isEmpty()) {
            Point<Integer, Integer> current = q.poll();

            for (final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.getRow() + DIRECTION[ROW];
                int nextCol = current.getCol() + DIRECTION[COL];

                Point<Integer, Integer> next = new Point.Builder(nextRow, nextCol).build();

                if(OUT_OF_RANGE.test(next)) continue;
                if(map[next.getRow()][next.getCol()] == BLOCK) continue;
                if(visit[next.getRow()][next.getCol()] != 0) continue;
                visit[next.getRow()][next.getCol()] = visit[current.getRow()][current.getCol()] + 1;

                q.offer(next);
            }
        }
    }

    private static final BiPredicate<Point<Integer, Integer>, Character> EXIT_FINDER = (p, target) ->
            (p.getRow() == 0 || p.getRow() == N - 1 || p.getCol() == 0 || p.getCol() == M - 1) && target == EMPTY;

    private static final Predicate<Point<Integer, Integer>> OUT_OF_RANGE = point ->
            point.getRow() < 0 || point.getRow() >= N || point.getCol() < 0 || point.getCol() >= M;
}
