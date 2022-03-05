package breadth_first_search;

import common.Point;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Predicate;

/**
 *
 * @author exponential-e
 * 백준 24513번: 좀비 바이러스
 *
 * @see https://www.acmicpc.net/problem/24513
 *
 */
public class Boj24513 {

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static int N, M;
    private static int[][] village;
    private static int[][] visit;

    private static Queue<Point> q = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        village = new int[N][M];
        visit = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < M; j++) {
                village[i][j] = Integer.parseInt(st.nextToken());

                if(village[i][j] <= 0) continue;
                q.offer(new Point.Builder(i, j).build());
                visit[i][j] = 1;
            }
        }

        System.out.println(bfs());
    }

    /**
     *
     * BFS
     *
     * line 66: except virus 3
     * line 77 ~ 88: spread virus & make virus 3
     *
     * @return
     */
    private static String bfs() {

        while(!q.isEmpty()) {
            int size = q.size();

            while(size-- > 0) {
                Point<Integer, Integer> current = q.poll();
                if(village[current.getRow()][current.getCol()] == 3) continue;

                for (final int[] DIRECTION : DIRECTIONS) {
                    int nextRow = DIRECTION[ROW] + current.getRow();
                    int nextCol = DIRECTION[COL] + current.getCol();

                    Point<Integer, Integer> next = new Point.Builder(nextRow, nextCol).build();

                    if (OUT_OF_RANGE.test(next)) continue;
                    if (village[next.getRow()][next.getCol()] == -1) continue;

                    if (visit[next.getRow()][next.getCol()] == 0) {
                        if(village[next.getRow()][next.getCol()] != 0) continue;
                        visit[next.getRow()][next.getCol()] = visit[current.getRow()][current.getCol()] + 1;
                        village[next.getRow()][next.getCol()] = village[current.getRow()][current.getCol()];

                        q.offer(next);
                    }
                    else {
                        if(visit[next.getRow()][next.getCol()] != visit[current.getRow()][current.getCol()] + 1) continue;
                        if(village[next.getRow()][next.getCol()] == village[current.getRow()][current.getCol()]) continue;
                        village[next.getRow()][next.getCol()] = 3;
                    }
                }
            }
        }

        return count(1) + " " + count(2) + " " + count(3);
    }

    private static int count(int virus) {
        int count = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(virus != village[i][j]) continue;
                count++;
            }
        }

        return count;
    }

    private static final Predicate<Point<Integer, Integer>> OUT_OF_RANGE = p ->
            p.getRow() < 0 || p.getRow() >= N || p.getCol() < 0 || p.getCol() >= M;
}
