package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 15573번: 채굴
 *
 * @see https://www.acmicpc.net/problem/15573/
 *
 */
public class Boj15573 {
    private static boolean[][] mined;
    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static int N, M;

    private static class Mine implements Comparable<Mine> {
        int row;
        int col;
        int val;

        public Mine (int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }

        @Override
        public int compareTo(Mine m) {
            if(this.val < m.val) return  -1;
            else if(this.val > m.val) return 1;
            else return 0;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(mining(K, map));
    }

    private static int mining (int k, int[][] map) {
        PriorityQueue<Mine> pq = new PriorityQueue<>();
        mined = new boolean[N][M];

        for(int i = 0; i < N; i++) {                            // contact with air
            pq.offer(new Mine(i, 0, map[i][0]));
            pq.offer(new Mine(i, M - 1, map[i][M - 1]));

            mined[i][0] = mined[i][M - 1] = true;
        }

        for(int i = 1; i < M - 1; i++) {
            pq.offer(new Mine(0, i, map[0][i]));

            mined[0][i] = true;
        }

        int max = 0;

        while(k > 0) {
            Mine current = pq.poll();
            max = Math.max(current.val, max);

            for(final int[] DIRECTION: DIRECTIONS) {
                int adjRow = current.row + DIRECTION[ROW];
                int adjCol = current.col + DIRECTION[COL];

                if(outOfRange(adjRow, adjCol)) continue;                    // next mine
                if(mined[adjRow][adjCol]) continue;
                mined[adjRow][adjCol] = true;

                pq.offer(new Mine(adjRow, adjCol, map[adjRow][adjCol]));
            }

            k--;
        }

        return max;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }
}
