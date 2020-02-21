package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1175번: 배달
 *
 * @see https://www.acmicpc.net/problem/1175/
 *
 */
public class Boj1175 {
    private static boolean[][][][][] visit;
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static Queue<Point> q = new LinkedList<>();

    private static int N, M;
    private static char[][] map;

    private static final char BLOCK = '#';

    private static class Point{
        int row;
        int col;
        int c1;
        int c2;
        int dir;

        public Point(int row, int col, int c1, int c2, int dir){
            this.row = row;
            this.col = col;
            this.c1 = c1;
            this.c2 = c2;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visit = new boolean[N][M][2][2][5];                                 // row col one another direction
        boolean flag = false;

        for(int i = 0; i < N; i++){
            String line = br.readLine();

            for(int j = 0; j < M; j++){
                map[i][j] = line.charAt(j);

                if(map[i][j] == 'S'){
                    q.offer(new Point(i, j, 0, 0, 4));          // find start
                    visit[i][j][0][0][4] = true;
                }

                if(map[i][j] == 'C') {
                    if(flag) map[i][j] = 'D';                               // make another destination
                    flag = true;
                }
            }
        }

        System.out.println(bfs());
    }

    private static int bfs(){
        int result = 0;

        while(!q.isEmpty()){
            int size = q.size();

            while(size-- > 0) {                                     // move FI's son
                Point current = q.poll();
                if(current.c1 * current.c2 == 1) return result;

                for (int nextDir = 0; nextDir < 4; nextDir++) {
                    int nextRow = current.row + DIRECTIONS[nextDir][ROW];
                    int nextCol = current.col + DIRECTIONS[nextDir][COL];
                    int nextC1 = current.c1;
                    int nextC2 = current.c2;

                    if (outOfRange(nextRow, nextCol)) continue;
                    if (map[nextRow][nextCol] == BLOCK || current.dir == nextDir) continue;
                    if (map[nextRow][nextCol] == 'C') nextC1 = 1;                       // find one
                    if (map[nextRow][nextCol] == 'D') nextC2 = 1;                       // find another
                    if (visit[nextRow][nextCol][nextC1][nextC2][nextDir]) continue;

                    visit[nextRow][nextCol][nextC1][nextC2][nextDir] = true;
                    q.offer(new Point(nextRow, nextCol, nextC1, nextC2, nextDir));
                }
            }

            result++;                   // move count
        }

        return -1;
    }

    private static boolean outOfRange(int row, int col){
        return row < 0 || row >= N || col < 0 || col >= M;
    }
}
