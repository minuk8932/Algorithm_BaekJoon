package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 4097번: 수익
 *
 * @see https://www.acmicpc.net/problem/4097
 *
 */
public class Boj4097 {

    private static final String NEW_LINE = "\n";
    private static final long INF = Long.MIN_VALUE;

    private static long[] profit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            profit = new long[N];
            for(int i = 0; i < N; i++) {
                profit[i] = Long.parseLong(br.readLine());
            }

            sb.append(section(N)).append(NEW_LINE);
        }

        System.out.println(sb);
    }

    private static long section(int n) {
        long answer = INF;
        long sum = 0;

        for(int i = 0; i < n; i++) {
            answer = Math.max(answer, profit[i]);

            if(sum + profit[i] <= 0) {
                sum = 0;
                continue;
            }

            sum += profit[i];
            answer = Math.max(sum, answer);
        }

        return answer;
    }

}
