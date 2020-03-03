package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 16973번: 직사각형 탈출
 *
 * @see https://www.acmicpc.net/problem/16973/
 *
 */
public class Boj16973 {
    private static Point start, end;
    private static int N, M, H, W;

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

        int[][] map = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken()) - 1;
        W = Integer.parseInt(st.nextToken()) - 1;

        start = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        end = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        System.out.println(bfs(map));
    }

    private static int bfs(int[][] map){
        int[][] visit = new int[N][M];

        Queue<Point> q = new LinkedList<>();
        q.offer(start);
        map[end.row][end.col] = 2;

        visit[start.row][start.col] = 1;

        while(!q.isEmpty()){
            Point current = q.poll();

            for(final int[] DIRECTION: DIRECTIONS){
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(outRange(nextRow, nextCol)) continue;
                if(visit[nextRow][nextCol] != 0 || isBlocked(nextRow, nextCol, map)) continue;
                visit[nextRow][nextCol] = visit[current.row][current.col] + 1;

                if(nextRow == end.row && nextCol == end.col) return visit[nextRow][nextCol] - 1;        // arrived
                q.offer(new Point(nextRow, nextCol));
            }
        }

        return -1;      // can not
    }

    private static boolean outRange(int row, int col){
        return row < 0 || col < 0 || row + H >= N || col + W >= M;
    }

    private static boolean isBlocked(int row, int col, int[][] arr) {
        for(int i = row; i <= row + H; i++){
            if(arr[i][col] == 1 || arr[i][col + W] == 1) return true;
        }

        for(int i = col; i <= col + W; i++){
            if(arr[row][i] == 1 || arr[row + H][i] == 1) return true;
        }

        return false;
    }
}
