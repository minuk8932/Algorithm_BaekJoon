import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj14651 {
	private static long[][] dp;
	private static int N;
	
	private static final int MOD = 1_000_000_009;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new long[N + 1][3];
		
		System.out.println(recursion(N, 0, 0, ""));
//		System.out.println(makeNumber());
	}
	
	private static long recursion(int n, int x, int sum, String num) {
		if(num.length() >= 1) {
            if(num.charAt(0) == '0') return 0;
        }
		
		if(n == 0) {			
			if(sum % 3 == 0) return 1;
			else return 0;
		}
		
		if(dp[n][x] != 0) return dp[n][x] % MOD;
		
		dp[n][x] = ((recursion(n - 1, 0, sum + 0, num + 0) % MOD) + (dp[n][x] % MOD)) % MOD;
		dp[n][x] = ((recursion(n - 1, 1, sum + 1, num + 1) % MOD) + (dp[n][x] % MOD)) % MOD;
		dp[n][x] = ((recursion(n - 1, 2, sum + 2, num + 2) % MOD) + (dp[n][x] % MOD)) % MOD;
		
		return dp[n][x] % MOD;
	}
}
