package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14546번: Prison Break
 *
 * @see https://www.acmicpc.net/problem/14546/
 *
 */
public class Boj14546 {
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
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            Point start = make(N, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            Point end = make(N, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            char[][] map = new char[N][M];
            for(int i = 0; i < N; i++){
                String line = br.readLine();

                for(int j = 0; j < M; j++){
                    map[i][j] = line.charAt(j);
                }
            }

            sb.append(bfs(N, M, map, start, end)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static Point make(int n, int input1, int input2){
        return new Point(n - input2, input1 - 1);
    }

    private static String bfs(int n, int m, char[][] arr, Point s, Point e){
        boolean[][] visit = new boolean[n][m];
        char mark = arr[s.row][s.col];

        Queue<Point> q = new LinkedList<>();
        q.offer(s);

        visit[s.row][s.col] = true;

        while(!q.isEmpty()){
            Point current = q.poll();

            for(final int[] DIRECTION: DIRECTIONS){
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
                if(visit[nextRow][nextCol] || arr[nextRow][nextCol] != mark) continue;
                visit[nextRow][nextCol] = true;

                if(nextRow == e.row && nextCol == e.col) return "YES";
                q.offer(new Point(nextRow, nextCol));
            }
        }

        return "NO";
    }
}
