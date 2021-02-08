package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 19606번: Escape Room
 *
 * @see https://www.acmicpc.net/problem/19606
 *
 */
public class Boj19606 {

    private static ArrayList<Point>[] path = new ArrayList[1_000_001];
    private static boolean[][] visit;

    private static int N, M;

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
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        visit = new boolean[N][M];

        for(int i = 0; i < path.length; i++) {
            path[i] = new ArrayList<>();
        }

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < M; j++) {
                int val = Integer.parseInt(st.nextToken());
                path[val].add(new Point(i, j));
            }
        }

        System.out.println(bfs());
    }

    /**
     *
     * Reversed Search BFS
     *
     * line 73: for reversed Search, saved data list[value].add(new Point(row, col))
     *
     * @return
     */
    private static String bfs() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(N - 1, M - 1));

        visit[N - 1][M - 1] = true;

        while(!q.isEmpty()) {
            Point current = q.poll();
            int value = (current.row + 1) * (current.col + 1);

            for(Point next: path[value]) {
                if(outOfRange(next.row, next.col)) continue;
                if(visit[next.row][next.col]) continue;
                visit[next.row][next.col] = true;

                if(next.row == 0 && next.col == 0) return "yes";
                q.offer(new Point(next.row, next.col));
            }
        }

        return "no";
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }
}
