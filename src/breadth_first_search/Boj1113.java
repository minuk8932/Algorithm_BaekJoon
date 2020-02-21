package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1113번: 수영장 만들기
 *
 * @see https://www.acmicpc.net/problem/1113/
 *
 */
public class Boj1113 {
    private static int min = 9, max = 1;
    private static int N, M;
    private static int[][] pool;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static class Point{
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pool = new int[N][M];
        for(int i = 0; i < N; i++){
            String line = br.readLine();

            for(int j = 0; j < M; j++){
                pool[i][j] = line.charAt(j) - '0';

                if(pool[i][j] > max) max = pool[i][j];
                if(pool[i][j] < min) min = pool[i][j];
            }
        }

        System.out.println(getPoolSize());
    }

    private static int getPoolSize(){
        int result = 0;

        for(int height = min; height <= max; height++){
            result += estimate(height);
        }

        return result;
    }

    private static int estimate(int h){
        boolean[][] visit = new boolean[N][M];
        int tmp = 0;

        for(int row = 0; row < N; row++){
            for(int col = 0; col < M; col++){
                if(pool[row][col] != h || visit[row][col]) continue;
                boolean flag = true;
                int filler = 10;

                visit[row][col] = true;
                Queue<Point> q = new LinkedList<>();
                LinkedList<Point> list = new LinkedList<>();

                list.add(new Point(row, col));
                q.offer(new Point(row, col));

                while (!q.isEmpty()) {
                    Point current = q.poll();

                    for (final int[] DIRECTION : DIRECTIONS) {
                        int adjRow = current.row + DIRECTION[ROW];
                        int adjCol = current.col + DIRECTION[COL];

                        if (overRange(adjRow, adjCol)) {            // if break the code visit array is not valid
                            flag = false;
                            continue;
                        }

                        if(pool[adjRow][adjCol] > h){
                            filler = Math.min(filler, pool[adjRow][adjCol]);
                            continue;
                        }

                        if(visit[adjRow][adjCol]) continue;
                        visit[adjRow][adjCol] = true;

                        list.add(new Point(adjRow, adjCol));            // save path
                        q.offer(new Point(adjRow, adjCol));
                    }
                }

                if(flag) tmp += filling(list, filler - h);          // pool filling
            }
        }

        return tmp;
    }

    private static int filling(LinkedList<Point> list, int f){
        int val = 0;

        while(!list.isEmpty()){
            Point p = list.remove();
            pool[p.row][p.col] += f;
            val += f;
        }

        return val;
    }

    private static boolean overRange(int row, int col){
        return row < 0 || row >= N || col < 0 || col >= M;
    }
}
