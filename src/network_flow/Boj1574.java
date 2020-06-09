package network_flow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1574번: 룩 어택
 *
 * @see https://www.acmicpc.net/problem/
 *
 */
public class Boj1574 {
    private static int[][] row, col, board, connected;
    private static int N, M, R, C;

    private static int[] aMatch, bMatch;
    private static int[] visit;

    private static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        row = new int[N][M];
        col = new int[N][M];

        int size = Math.max(N, M);
        connected = new int[size + 1][size + 1];
        visit = new int[size + 1];

        while(K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            board[r][c] = 1;
        }

        col = modeling(true);           // right side
        row = modeling(false);          // left side

        connection();                        // setting capacity
        System.out.println(bipartiteMath());
    }

    private static int bipartiteMath() {
        aMatch = new int[N];
        bMatch = new int[M];
        Arrays.fill(aMatch, -1);
        Arrays.fill(bMatch, -1);

        int result = 0;

        for(int i = 0; i < N; i++) {
            count++;
            result += recursion(i);
        }

        return result;
    }

    private static int recursion(int current) {
        if (visit[current] == count) return 0;
        visit[current] = count;

        for(int next = 0; next < M; next++) {
            if(connected[current][next] == 0) continue;

            if(bMatch[next] == -1 || recursion(bMatch[next]) == 1) {
                aMatch[current] = next;
                bMatch[next] = current;

                return 1;
            }
        }

        return 0;
    }

    private static void connection() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != 0) continue;
                connected[row[i][j]][col[i][j]] = 1;    // from -> to, capacity

                R = Math.max(R, row[i][j]);             // resize
                C = Math.max(C, col[i][j]);
            }
        }

        N = R + 1;
        M = C + 1;
    }

    private static int[][] modeling(boolean flag) {
        int[][] arr = new int[N][M];

        int outer = flag ? N: M;
        int inner = flag ? M: N;
        int value = 0;

        for (int i = 0; i < outer; i++) {
            boolean used = false;

            for (int j = 0; j < inner; j++) {
                int[] index = {flag ? i: j, flag ? j: i};

                if (board[index[0]][index[1]] == 0 || board[index[0]][index[1]] == 1) {
                    arr[index[0]][index[1]] = value;
                    used = true;
                }
                else {
                    if(!used) continue;

                    value++;
                    used = false;
                }
            }

            if (used) value++;
        }

        return arr;
    }
}
