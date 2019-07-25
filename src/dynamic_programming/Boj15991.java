package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 15991번: 1, 2, 3 더하기 6
 *
 *	@see https://www.acmicpc.net/problem/15991/
 *
 */
public class Boj15991 {
	private static final String NEW_LINE = "\n";
	private static final int MOD = 1_000_000_009;
	
	private static long[] dp = new long[50_002];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		makeFormula(dp.length);
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			sb.append(((dp[N / 2] % MOD) + (dp[N / 2 + 1] % MOD)) % MOD).append(NEW_LINE);	// 구성: 홀수개, 짝수개
		}
		
		System.out.println(sb.toString());
	}
	
	private static void makeFormula(int N) {
		dp[1] = dp[2] = 1;
		
		for(int i = 3; i < N; i++) {
			dp[i] = ((dp[i - 1] % MOD) + (dp[i - 2] % MOD) + (dp[i - 3] % MOD)) % MOD;		// 현재 값 i에서 1, 2, 3를 이용해 구하는 경우의 수
		}
	}
	
//	private static long recursion(int n, int k) {
//		if(n == 0 || n == 1) return 1;
//		if(n == 2 || n == 3) return 2;
//		
//		if(n < 0) return 0;
//		if(dp[n][k] != 0) return dp[n][k] % MOD;
//		
//		for(int i = 1; i < 4; i++) {
//			int mul = i * 2;
//			if(n - mul >= 0) dp[n][k] = ((recursion(n - mul, i) % MOD) + (dp[n][k] % MOD)) % MOD;
//		}
//		
//		return dp[n][k] % MOD;
//	}
}
