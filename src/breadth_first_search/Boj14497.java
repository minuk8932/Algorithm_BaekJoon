package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14497번: 주난의 난
 *
 * @see https://www.acmicpc.net/problem/14497/
 *
 */
public class Boj14497 {
    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static Point start, end;
    private static int N, M;

    private static class Point{
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

        st = new StringTokenizer(br.readLine());
        start = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        end = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        boolean[][] map = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();

            for(int j = 0; j < M; j++) {
                int c = line.charAt(j) - '0';
                map[i][j] = c != 0;
            }
        }

        System.out.println(bfs(map));
    }

    private static int bfs(boolean[][] map){
        int[][] visit = new int[N][M];
        for(int i = 0; i < N; i++){
            Arrays.fill(visit[i], 1_000_000);
        }

        Queue<Point> q = new LinkedList<>();
        q.offer(start);
        visit[start.row][start.col] = 1;

        while(!q.isEmpty()) {
            int size = q.size();

            while(size-- > 0) {
                Point current = q.poll();

                for (final int[] DIRECTION : DIRECTIONS) {
                    int nextRow = current.row + DIRECTION[ROW];
                    int nextCol = current.col + DIRECTION[COL];
                    int adder = 0;

                    if (outOfRange(nextRow, nextCol)) continue;
                    if (map[nextRow][nextCol]) adder++;             // if blocked
                    if (visit[nextRow][nextCol] <= visit[current.row][current.col] + adder) continue;
                    visit[nextRow][nextCol] = visit[current.row][current.col] + adder;

                    q.offer(new Point(nextRow, nextCol));
                }
            }
        }

        return visit[end.row][end.col] - 1;                         // get wave count
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }
}
