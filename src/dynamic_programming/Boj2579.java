package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2579 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int[] score = new int[t + 1];
		
		for(int i = 1; i < t + 1; i++){
			score[i] = Integer.parseInt(br.readLine());
		}
		
		int[][] dp = new int[t + 1][2];
		
		dp[1][0] = dp[1][1] = score[1];
		
		for (int i = 2; i < t + 1; i++) {
            dp[i][0] = dp[i - 1][1] + score[i];
            dp[i][1] = Math.max(dp[i - 2][0], dp[i - 2][1]) + score[i];
        }
		System.out.println(Math.max(dp[t][1], dp[t][0]));
	}
}
