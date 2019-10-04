package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 2011번: 암호 해독
 *
 *	@see https://www.acmicpc.net/problem/2011/
 *
 */
public class Boj2011 {
	private static long[] dp;
	private static final int MOD = 1_000_000;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] pw = br.readLine().toCharArray();
		
		System.out.println(decoder(pw.length, pw));
	}
	
	private static long decoder(int n, char[] pw) {
		if(pw[0] == '0') return 0;
		if(n == 0) return 1;
		
		dp = new long[n + 1];
		dp[0] = 1;					// non pw
		dp[1] = 1;
		
		for(int i = 2; i <= n; i++) {
			int val = pw[i - 1] - '0';
			int prev = pw[i - 2] - '0';
			
			if(val != 0) dp[i] = ((dp[i - 1] % MOD) + (dp[i] % MOD)) % MOD;				// 1 ~ 9
			
			int two = prev * 10 + val;
			if(two <= 26 && two > 9) dp[i] = ((dp[i] % MOD) + (dp[i - 2] % MOD)) % MOD;	// 10 ~ 26
		}
		
		return dp[n];
	}
}
