package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18405번: 경쟁적 전염
 *
 * @see https://www.acmicpc.net/problem/18405/
 *
 */
public class Boj18405 {
    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static ArrayList<Point>[] virus;
    private static int N, K;

    private static class Point {
        int row;
        int col;

        public Point (int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        virus = new ArrayList[K];
        for (int i = 0; i < K; i++){
            virus[i] = new ArrayList<>();
        }

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 0) continue;
                virus[map[i][j] - 1].add(new Point(i, j));
            }
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()) - 1;
        int Y = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(bfs (map, S, X, Y));
    }

    private static int bfs (int[][] m, int s, int x, int y){
        Queue<Point> q = new LinkedList<>();

        for (int i = 0; i < K; i++) {                                       // make priority
            for(Point start: virus[i]){
                q.offer(start);
            }
        }

        while (!q.isEmpty() && s-- > 0) {                                   // time count
            int size = q.size();

            while(size-- > 0){
                Point current = q.poll();

                for (int[] DIRECTION: DIRECTIONS) {
                    int nextRow = current.row + DIRECTION[ROW];
                    int nextCol = current.col + DIRECTION[COL];

                    if (outOfRange(nextRow, nextCol)) continue;
                    if (m[nextRow][nextCol] != 0) continue;
                    m[nextRow][nextCol] = m[current.row][current.col];      // infection

                    q.offer(new Point(nextRow, nextCol));
                }
            }
        }

        return m[x][y];
    }

    private static boolean outOfRange (int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }
}
