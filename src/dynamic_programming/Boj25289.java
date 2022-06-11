package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25289번: 가장 긴 등차 부분 수열
 *
 * @see https://www.acmicpc.net/problem/25289
 *
 */
public class Boj25289 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(subSequence(N, A));
    }

    private static int subSequence(int n, int[] a) {
        int answer = 0;

        for (int interval = -99; interval <= 99; interval++) {
            int[] dp = new int[101];

            for (int i = 0; i < n; i++) {
                int difference = a[i] - interval;

                if(difference >= 1 && difference <= 100) {
                    dp[a[i]] = dp[difference] + 1;
                }
                else {
                    dp[a[i]] = 1;
                }

                answer = Math.max(answer, dp[a[i]]);
            }
        }

        return answer;
    }
}
