package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 7489번: 팩토리얼
 *
 * @see https://www.acmicpc.net/problem/7489/
 *
 */
public class Boj7489 {
    private static long[] dp = new long[1_001];

    private static final String NEW_LINE = "\n";
    private static final long MOD = 10_000_000_000L;
    private static final int CIPHER = 10;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        factorial();

        StringBuilder sb = new StringBuilder();

        while(t-- > 0){
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n] % CIPHER).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void factorial(){
        dp[1] = 1;

        for(int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] * i;
            while (dp[i] % CIPHER == 0) {       // make factorial
                dp[i] = dp[i] / CIPHER;
            }

            dp[i] = dp[i] % MOD;
        }
    }
}
