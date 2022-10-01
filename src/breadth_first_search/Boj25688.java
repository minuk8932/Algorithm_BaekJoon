package breadth_first_search;

import common.Point;
import java.io.*;
import java.util.*;
import java.util.function.BiPredicate;

/**
 *
 * @author exponential-e
 * 백준 25688번: 빠른 무작위 숫자 탐색
 *
 * @see https://www.acmicpc.net/problem/25688
 *
 */
public class Boj25688 {

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static final int NON_VISIT = 1_000_000_000;

    private static final int N = 5;
    private static final BiPredicate<Integer, Integer> OUT_OF_RANGE = (row, col) ->
        row < 0 || row >= N || col < 0 || col >= N;
    private static int[][] visit = new int[N][N];
    private static int[][] map = new int[N][N];
    private static Point<Integer, Integer> start;
    private static Point<Integer, Integer> init;

    private static ArrayList<Integer> permutation = new ArrayList<>();
    private static boolean[] used;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int start = 1; start <= 6; start++) {
            used = new boolean[6];
            recursion(start - 1, start, 1);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        init = new Point.Builder<Integer, Integer>(
            Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())).build();

        System.out.println(searching());
    }

    private static int searching() {
        int answer = NON_VISIT;

        for(int perm: permutation) {
            int loop = perm;
            int dist = 0;

            start = init;

            while(loop > 0) {
                int destination = loop % 10;
                loop /= 10;

                for (int i = 0; i < N; i++) {
                    Arrays.fill(visit[i], NON_VISIT);
                }

                int cost = bfs(destination);
                dist += cost;

                if (cost != -1) continue;
                dist = NON_VISIT;
                break;
            }

            answer = Math.min(dist, answer);
        }

        return answer == NON_VISIT ? -1: answer;
    }

    private static void recursion(int current, int value, int count) {
        if(count == 6) {
            permutation.add(value);
            return;
        }

        used[current] = true;
        for(int next = 1; next <= 6; next++) {
            if(used[next - 1]) continue;
            recursion(next - 1, value * 10 + next, count + 1);
            used[next - 1] = false;
        }
    }

    private static int bfs(int destination) {
        Queue<Point<Integer, Integer>> q = new ArrayDeque<>();
        q.offer(start);

        visit[start.getRow()][start.getCol()] = 0;

        while(!q.isEmpty()) {
            Point<Integer, Integer> current = q.poll();

            for (final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.getRow() + DIRECTION[ROW];
                int nextCol = current.getCol() + DIRECTION[COL];

                if(OUT_OF_RANGE.test(nextRow, nextCol)) continue;
                if(map[nextRow][nextCol] == -1) continue;
                if(visit[nextRow][nextCol] != NON_VISIT) continue;
                visit[nextRow][nextCol] = visit[current.getRow()][current.getCol()] + 1;

                if(map[nextRow][nextCol] == destination){
                    start = new Point.Builder<Integer, Integer>(nextRow, nextCol).build();
                    return visit[nextRow][nextCol];
                }

                q.offer(new Point.Builder<Integer, Integer>(nextRow, nextCol).build());
            }
        }

        return -1;
    }
}
