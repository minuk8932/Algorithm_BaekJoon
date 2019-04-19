package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 15989번: 1, 2, 3 더하기 4
 *
 *	@see https://www.acmicpc.net/problem/15989/
 *
 */
public class Boj15989 {
	private static int[][] dp;
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		getSequence();
		
		while(T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			sb.append(dp[n][1] + dp[n][2] + dp[n][3]).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static void getSequence() {
		dp = new int[10_001][4];
		dp[1][1] = dp[2][1] = dp[2][2] = dp[3][1] = dp[3][2] = dp[3][3] = 1;
		
		for(int i = 4; i < 10_001; i++) {
			for(int j = 1; j < 4; j++) {
				int sum = 0;
				
				if(j == 1) {
					sum = 1;
				}
				else {
					for(int k = 1; k < j + 1; k++) {
						sum += dp[i - j][k];			// i에 j를 이용해 수를 채울 수 있는 경우의 수
					}									// ex: 4 - 2 -> 나머지는 2이하와 1이하의 수로 채울 수 있는 경우의 수
				}
				
				dp[i][j] = sum;
			}
		}
	}
}
