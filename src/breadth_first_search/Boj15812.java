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
 * 백준 15812번: 침략자 진아
 *
 * @see https://www.acmicpc.net/problem/15812/
 *
 */
public class Boj15812 {
    private static int N, M;
    private static int min = Integer.MAX_VALUE;

    private static int[][] map;

    private static boolean[] tracking;
    private static boolean[] block;
    private static int[] arr = new int[2];
    private static ArrayList<Point> town = new ArrayList<>();

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static class Point{
        int row;
        int col;

        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        tracking = new boolean[N * M];
        block = new boolean[N * M];
        for(int i = 0; i < N; i++){
            String line = br.readLine();

            for(int j = 0; j < M; j++){
                map[i][j] = line.charAt(j) - '0';

                if(map[i][j] == 1){             // find village
                    town.add(new Point(i, j));
                    block[i * M + j] = true;
                }
            }
        }

        for(int i = 0; i < N * M; i++) {
            if(block[i]) continue;
            backTracking(i, 0);
        }

        System.out.println(min);
    }

    private static void backTracking(int current, int count){
        arr[count] = current;
        tracking[current] = true;

        if(count == 1){
            int val = bfs(arr[0], arr[1]);      // make all cases
            if(val < min) min = val;
            return;
        }

        for(int next = current; next < N * M; next++){
            if(tracking[next] || block[next]) continue;

            backTracking(next, count + 1);
            tracking[next] = false;
        }
    }

    private static int bfs(int a, int b){
        Queue<Point> q = new LinkedList<>();
        Point ap = new Point(a / M, a % M);
        Point bp = new Point(b / M, b % M);

        q.offer(ap);
        q.offer(bp);

        int[][] visit = new int[N][M];
        visit[ap.row][ap.col] = 1;
        visit[bp.row][bp.col] = 1;

        while(!q.isEmpty()){
            Point current = q.poll();

            for (final int[] DIRECTION : DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if (outRange(nextRow, nextCol)) continue;
                if (visit[nextRow][nextCol] != 0) continue;
                visit[nextRow][nextCol] = visit[current.row][current.col] + 1;

                q.offer(new Point(nextRow, nextCol));
            }
        }

        int res = 1;

        for(Point p: town){
            if(res < visit[p.row][p.col]) res = visit[p.row][p.col];        // get total time
        }

        return res - 1;
    }

    private static boolean outRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }
}
