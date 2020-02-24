package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17779번: 게리멘더링2
 *
 * @see https://www.acmicpc.net/problem/17779
 *
 */
public class Boj17779 {
    private static int[][] map;
    private static int N;
    private static boolean[][] used;

    private static int[] district;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static Pair[] starter;

    private static class Pair{
        int row;
        int col;

        public Pair(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(gerryMandering());
    }

    private static int gerryMandering(){
        int min = Integer.MAX_VALUE;

        starter = new Pair[N];
        starter[0] = new Pair(0, 0);
        starter[1] = new Pair(0, N - 1);
        starter[2] = new Pair(N - 1, 0);
        starter[3] = new Pair(N - 1, N - 1);

        for (int row = 0; row < N; row++) {
            for (int col = row; col < N; col++) {
                for(int d1 = 1; d1 < N; d1++) {
                    for(int d2 = 1; d2 < N; d2++) {
                        if(col - d1 < 0 || col + d2 >= N || row + d1 + d2 >= N) continue;
                        district = new int[5];
                        used = new boolean[N][N];

                        district[4] = makeDistrictFive(new Pair(row, col), new Pair(d1, d2));
                        district[0] = makeDistrict(new Pair(0, 0), new Pair(row + d1 - 1, col), 1);
                        district[1] = makeDistrict(new Pair(0, col + 1), new Pair(row + d2, N - 1), 2);
                        district[2] = makeDistrict(new Pair(row + d1, 0), new Pair(N - 1, col - d1 + d2 - 1), 3);
                        district[3] = makeDistrict(new Pair(row + d2 + 1, col - d1 + d2), new Pair(N - 1, N - 1), 4);

                        Arrays.sort(district);
                        min = Math.min(min, district[4] - district[0]);     // get min value
                    }
                }
            }
        }

        return min;
    }

    private static int makeDistrict(Pair start, Pair end, int idx){         // make district 1 ~ 4
        Queue<Pair> q = new LinkedList<>();
        q.offer(starter[idx - 1]);

        int sum = map[starter[idx - 1].row][starter[idx - 1].col];
        used[starter[idx - 1].row][starter[idx - 1].col] = true;

        while(!q.isEmpty()){
            Pair current = q.poll();

            for(final int[] DIRECTION: DIRECTIONS){
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(nextRow < start.row || nextCol < start.col || nextRow > end.row || nextCol > end.col) continue;
                if(used[nextRow][nextCol]) continue;
                used[nextRow][nextCol] = true;

                sum += map[nextRow][nextCol];
                q.offer(new Pair(nextRow, nextCol));
            }
        }

        return sum;
    }

    private static int makeDistrictFive(Pair p, Pair d){                // make district 5
        int sum = 0;

        for(int r = 0; r <= d.row; r++){
            for(int c = 0; c <= d.col; c++){
                int nextRow = p.row + c + r;
                int nextCol = p.col + c - r;
                int adj = nextRow + 1;

                if(adj <= p.row + r + d.col && adj <= p.row + d.row + c){
                    used[adj][nextCol] = true;
                    sum += map[adj][nextCol];
                }

                used[nextRow][nextCol] = true;
                sum += map[nextRow][nextCol];
            }
        }

        return sum;
    }
}
