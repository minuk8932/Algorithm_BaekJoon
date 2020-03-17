package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 12888번: 완전 이진 트리 도로 네트워크
 *
 * @see https://www.acmicpc.net/problem/12888/
 *
 */
public class Boj12888 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(tour(Integer.parseInt(br.readLine())));
    }

    private static long tour(int H){
        if(H == 0 || H == 1) return 1;

        long[] dp = new long[H + 1];

        dp[0] = dp[1] = 1;
        long sum = 0;

        for(int i = 2; i <= H; i++){
            sum += dp[i - 2] * 2;
            dp[i] = sum + 1;
        }

        return dp[H];
    }
}
