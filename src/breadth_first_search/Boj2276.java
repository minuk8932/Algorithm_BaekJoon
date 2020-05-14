package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2276번: 물 채우기
 *
 * @see https://www.acmicpc.net/problem/2276/
 *
 */
public class Boj2276 {
    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static PriorityQueue<Point> pq = new PriorityQueue<>();
    private static boolean[][] visit;
    private static int N, M;
    private static int result;

    private static class Point implements Comparable<Point>{
        int row;
        int col;
        int depth;

        public Point(int row, int col, int depth) {
            this.row = row;
            this.col = col;
            this.depth = depth;
        }

        @Override
        public int compareTo(Point p) {
            return this.depth < p.depth ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        int[][] bottle = new int[N][M];
        visit = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < M; j++) {
                bottle[i][j] = Integer.parseInt(st.nextToken());

                if(i != 0 && j != 0 && i != N - 1 && j != M - 1) continue;
                pq.offer(new Point(i, j, bottle[i][j]));                    // save candidate
                visit[i][j] = true;
            }
        }

        while(!pq.isEmpty()) {
            Point current = pq.poll();
            bfs(bottle, current);
        }

        System.out.println(result);
    }

    private static void bfs(int[][] arr, Point start) {
        Queue<Point> q = new LinkedList<>();
        q.offer(start);

        while(!q.isEmpty()) {
            Point current = q.poll();

            for(int[] DIRECTION: DIRECTIONS) {
                Point next = new Point(current.row + DIRECTION[ROW], current.col + DIRECTION[COL], 0);

                if(outOfRange(next.row, next.col)) continue;
                if(visit[next.row][next.col]) continue;
                visit[next.row][next.col] = true;

                next.depth = arr[next.row][next.col];

                if(next.depth > start.depth){
                    pq.offer(next);
                    continue;
                }

                result += start.depth - next.depth;     // lower floor filling by adjacent the smallest
                q.offer(next);
            }
        }
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }
}
