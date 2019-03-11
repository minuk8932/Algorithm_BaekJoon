package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2688번: 줄어들지 않아
 *
 *	@see https://www.acmicpc.net/problem/2688
 *
 */
public class Boj2688 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			sb.append(count(n)).append(NEW_LINE);
		}
		
		System.out.print(sb);
	}
	
	private static long count(int N) {
		long[][] dp = new long[N + 1][10];
		
		for(int i = 0; i < 10; i++) {
			dp[1][i] = 1;
		}
		
		for(int i = 2; i < N + 1; i++) {			// i 자리수에서
			dp[i][0] = 1;
			
			for(int j = 1; j < 10; j++) {
				for(int k = 0; k < j + 1; k++) {	// j보다 작은 k의 경우의 수
					dp[i][j] += dp[i - 1][k];
				}
			}
		}
		
		long max = 0;
		for(int i = 0; i < 10; i++) {
			max += dp[N][i];
		}
		
		return max;
	}
}
