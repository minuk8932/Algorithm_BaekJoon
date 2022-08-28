package breadth_first_search;

import common.Point;
import java.io.*;
import java.util.*;
import java.util.function.BiPredicate;

/**
 *
 * @author exponential-e
 * 백준 22513번: 빠른 오름차순 숫자 탐색
 *
 * @see https://www.acmicpc.net/problem/22513
 *
 */
public class Boj22513 {

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static final int NON_VISIT = -1;

    private static final int N = 5;
    private static final BiPredicate<Integer, Integer> OUT_OF_RANGE = (row, col) ->
        row < 0 || row >= N || col < 0 || col >= N;
    private static int[][] visit = new int[N][N];
    private static int[][] map = new int[N][N];
    private static ArrayList<Point<Integer, Integer>> ends = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] <= 0) continue;
                ends.add(new Point.Builder<Integer, Integer>(i, j).cost(map[i][j]).build());
            }
        }

        ends.sort(Comparator.comparingInt(Point::getCost));

        StringTokenizer st = new StringTokenizer(br.readLine());
        Point start = new Point.Builder<Integer, Integer>(
            Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())).cost(0).build();

        int answer = 0;
        for(int destination = 1; destination <= 6; destination++) {
            for(int i = 0; i < N; i++) {
                Arrays.fill(visit[i], NON_VISIT);
            }

            int cost = bfs(destination, start);
            start = ends.get(destination - 1);
            answer += cost;

            if(cost > NON_VISIT) continue;
            answer = NON_VISIT;
            break;
        }

        System.out.println(answer);
    }

    private static int bfs(int destination, Point<Integer, Integer> start) {
        Queue<Point<Integer, Integer>> q = new ArrayDeque<>();
        q.offer(start);

        visit[start.getRow()][start.getCol()] = 0;

        while(!q.isEmpty()) {
            Point<Integer, Integer> current = q.poll();

            for (final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.getRow() + DIRECTION[ROW];
                int nextCol = current.getCol() + DIRECTION[COL];

                if(OUT_OF_RANGE.test(nextRow, nextCol)) continue;
                if(map[nextRow][nextCol] == NON_VISIT) continue;
                if(visit[nextRow][nextCol] != NON_VISIT) continue;
                visit[nextRow][nextCol] = visit[current.getRow()][current.getCol()] + 1;

                if(map[nextRow][nextCol] == destination) return visit[nextRow][nextCol];
                q.offer(new Point.Builder<Integer, Integer>(nextRow, nextCol).cost(0).build());
            }
        }

        return -1;
    }
}
