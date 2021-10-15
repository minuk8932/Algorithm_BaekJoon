package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 9879번: Cross country skiing
 *
 * @see https://www.acmicpc.net/problem/9879
 *
 */
public class Boj9879 {

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static final int INF = 1_000_000_000;

    private static int N, M;

    private static List<Point> station = new ArrayList<>();
    private static boolean[][] visit;
    private static int[][] map;

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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < M; j++) {
                int point = Integer.parseInt(st.nextToken());
                if(point == 0) continue;

                station.add(new Point(i, j));
            }
        }

        System.out.println(binarySearch());
    }

    /**
     *
     * BinarySearch
     * set limit
     *
     * @return
     */
    private static int binarySearch() {
        int start = 0;
        int end = INF;

        int answer = 0;

        while (start <= end) {
            int mid = (start + end) >> 1;

            visit = new boolean[N][M];
            boolean travel = bfs(mid);

            if(travel) {
                end = mid - 1;
                answer = mid;
            }
            else {
                start = mid + 1;
            }
        }

        return answer;
    }

    private static boolean bfs(int limit) {

        Queue<Point> q = new ArrayDeque<>();

        Point start = station.get(0);
        q.offer(start);

        visit[start.row][start.col] = true;

        while(!q.isEmpty()) {
            Point current = q.poll();

            for (final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(outOfRange(nextRow, nextCol) || visit[nextRow][nextCol]) continue;
                if(Math.abs(map[nextRow][nextCol] - map[current.row][current.col]) > limit) continue;
                visit[nextRow][nextCol] = true;

                q.offer(new Point(nextRow, nextCol));
            }
        }

        for(Point p: station) {
            if(!visit[p.row][p.col]) return false;
        }

        return true;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || col < 0 || row >= N || col >= M;
    }
}
