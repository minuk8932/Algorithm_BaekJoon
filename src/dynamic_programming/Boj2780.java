package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2780번: 비밀번호
 *
 *	@see https://www.acmicpc.net/problem/2780/
 *
 */
public class Boj2780 {
	private static final int MOD = 1_234_567;	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int length = Integer.parseInt(br.readLine());
			sb.append(makePassword(length)).append(NEW_LINE);
		}
		
		System.out.print(sb);
	}
	
	private static int makePassword(int N) {
		if(N == 0) return 0;
		
		int[][] dp = new int[10][N + 1];		// [시작하는 숫자][N자리]
		
		for(int i = 0; i < 10; i++) {
			dp[i][1] = 1;
		}
		
		for(int i = 2; i < N + 1; i++) {
			dp[0][i] = dp[7][i - 1] % MOD;
			dp[1][i] = modular(dp[2][i - 1], dp[4][i - 1], 0, 0);
			dp[2][i] = modular(dp[1][i - 1], dp[3][i - 1], dp[5][i - 1], 0);
			dp[3][i] = modular(dp[2][i - 1], dp[6][i - 1], 0, 0);
			dp[4][i] = modular(dp[1][i - 1], dp[5][i - 1], dp[7][i - 1], 0);
			dp[5][i] = modular(dp[2][i - 1], dp[4][i - 1], dp[6][i - 1], dp[8][i - 1]);
			dp[6][i] = modular(dp[3][i - 1], dp[5][i - 1], dp[9][i - 1], 0);
			dp[7][i] = modular(dp[0][i - 1], dp[4][i - 1], dp[8][i - 1], 0);
			dp[8][i] = modular(dp[5][i - 1], dp[7][i - 1], dp[9][i - 1], 0);
			dp[9][i] = modular(dp[6][i - 1], dp[8][i - 1], 0, 0);
		}
		
		return getSum(N, dp);		// 비밀번호 종류 갯수
	}
	
	private static int modular(int a, int b, int c, int d) {
		return ((a % MOD) + (b % MOD) + (c % MOD) + (d % MOD)) % MOD;
	}
	
	private static int getSum(int N, int[][] dp) {
		int sum = 0;
		
		for(int i = 0; i < 10; i++) {
			sum = modular(sum, dp[i][N], 0, 0);
		}
		
		return sum;
	}
}
