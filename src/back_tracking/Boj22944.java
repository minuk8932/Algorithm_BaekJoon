package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 22944번: 죽음의 비
 *
 * @see https://www.acmicpc.net/problem/22944
 *
 */
public class Boj22944 {

    private static final int INF = 1_000_000_000;

    private static int N, D;
    private static int size;
    private static int answer = INF;

    private static Point start;
    private static Point end;

    private static Map<Integer, Point> umbrellas = new HashMap<>();
    private static boolean[] visit;

    private static final char START = 'S';
    private static final char END = 'E';
    private static final char UMBRELLA = 'U';

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
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = PARSE.apply(st.nextToken());
        int H = PARSE.apply(st.nextToken());
        D = PARSE.apply(st.nextToken());

        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            for(int j = 0; j < N; j++) {
                char data = line.charAt(j);

                if(data == START) start = new Point(i, j);
                if(data == END) end = new Point(i, j);
                if(data == UMBRELLA) umbrellas.put(size++, new Point(i, j));
            }
        }

        visit = new boolean[size];
        recursion(start, H, 0, 0);

        System.out.println(answer == INF ? -1: answer);
    }

    /**
     *
     * Backtracking
     *
     * line 83 ~ 86: check reachable to end
     * line 92: find reachable
     *
     * @param current
     * @param health
     * @param duration
     * @param distance
     */
    private static void recursion(Point current, int health, int duration, int distance) {
        int ending = MANHATTAN_DISTANCE.apply(current, end);

        if (ending <= health + duration) {
            answer = Math.min(answer, distance + ending);
            return;
        }

        for (int i = 0; i < size; i++) {
            Point next = umbrellas.get(i);

            int cost = MANHATTAN_DISTANCE.apply(current, next);
            if (health + duration < cost || visit[i]) continue;

            visit[i] = true;
            recursion(next, health + (duration < cost ? duration - cost : 0), D, distance + cost);
            visit[i] = false;
        }
    }

    private static final Function<String, Integer> PARSE = Integer::parseInt;

    private static final BiFunction<Point, Point, Integer> MANHATTAN_DISTANCE = (p1, p2) ->
            Math.abs(p1.row - p2.row) + Math.abs(p1.col - p2.col);
}
