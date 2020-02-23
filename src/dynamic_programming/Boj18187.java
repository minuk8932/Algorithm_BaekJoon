package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 18187번: 평면 분할
 *
 * @see https://www.acmicpc.net/problem/18187/
 *
 */
public class Boj18187 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(division(Integer.parseInt(br.readLine())));
    }

    private static int division(int n){
        int[] dp = new int[n];
        int adder = 2;
        dp[0] = 2;

        for(int i = 1; i < n; i++){
            dp[i] = dp[i - 1] + ((i + 1) % 3 == 0 ? adder: adder++);        // repeat at 3 multiples
        }

        return dp[n - 1];
    }
}
