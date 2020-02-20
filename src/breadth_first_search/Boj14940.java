package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14940번: 쉬운 최단거리
 *
 * @see https://www.acmicpc.net/problem/14940/
 *
 */
public class Boj14940 {
    private static Point start;

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

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
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) start = new Point(i, j);     // get start
            }
        }

        bfs(n, m, map);
    }

    private static void bfs(int n, int m, int[][] map){
        Queue<Point> q = new LinkedList<>();
        int[][] visit = new int[n][m];

        q.offer(start);
        visit[start.row][start.col] = 1;

        while(!q.isEmpty()){
            Point current = q.poll();

            for(final int[] DIRECTION: DIRECTIONS){
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= m) continue;
                if(visit[nextRow][nextCol] != 0 || map[nextRow][nextCol] == 0) continue;

                visit[nextRow][nextCol] = visit[current.row][current.col] + 1;
                q.offer(new Point(nextRow, nextCol));
            }
        }

        print(visit, map);
    }

    private static void print(int[][] arr, int[][] map){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                if(map[i][j] == 0) sb.append(0).append(SPACE);              // blocks
                else if(arr[i][j] == 0) sb.append(-1).append(SPACE);        // can't reach
                else sb.append(arr[i][j] - 1).append(SPACE);                // road
            }
            sb.append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }
}
