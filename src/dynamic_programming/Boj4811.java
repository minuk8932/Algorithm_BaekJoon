package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 4811번: 알약
 *
 *	@see https://www.acmicpc.net/problem/4811/
 *
 */
public class Boj4811 {
	private static final String NEW_LINE = "\n";
	private static long[][] dp = new long[61][61];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		combination(60, 60);
		
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			
			sb.append(dp[N * 2][N] / (long) (N + 1)).append(NEW_LINE);	// Catalan Number를 버퍼에 저장, 공식: (n^2)Cn / (n + 1)
		}
		
		System.out.println(sb);		// 결과 출력
	}
	
	/**
	 * Combination by Dynaminc Programming Method
	 * 
	 * @param n
	 * @param r
	 */
	private static void combination(int n, int r) {
		for(int i = 0; i < n + 1; i++) {
			dp[i][i] = dp[i][0] = 1;
		}
		
		for(int i = 1; i < n + 1; i++) {
			for(int j = 1; j < n + 1; j++) {
				if(i == j || j == 0) continue;
				
				dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
			}
		}
	}
}
