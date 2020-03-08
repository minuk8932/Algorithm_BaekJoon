package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18229번: 내가 살게, 아냐 내가 살게
 *
 * @see https://www.acmicpc.net/problem/18229/
 *
 */
public class Boj18229 {
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] log = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < M; j++){
                log[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(pay(N, M, K, log));
    }

    private static String pay(int n, int m, int k, int[][] log){
        StringBuilder sb = new StringBuilder();
        int[] card = new int[n];
        int[] count = new int[n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                card[j] += log[j][i];
                count[j]++;

                if(card[j] >= k) return sb.append(j + 1).append(SPACE).append(count[j]).toString();
            }
        }

        return sb.append(1).append(SPACE).append(1).toString();
    }
}
