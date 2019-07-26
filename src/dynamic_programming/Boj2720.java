package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 2720번: 세탁소 사장 동혁
 *
 *	@see https://www.acmicpc.net/problem/2720/
 *
 */
public class Boj2720 {
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	private static boolean[] dp;
	private static int[] coins = {25, 10, 5, 1};
	private static int[] result = new int[4];
	private static int min;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int C = Integer.parseInt(br.readLine());
			dp = new boolean[C + 1];
			min = 1_000;
			
			recursion(C, 0, 0, 0, 0);
			for(int i = 0; i < 4; i++) {
				sb.append(result[i]).append(SPACE);
			}
			
			sb.append(NEW_LINE);
		}
		
		System.out.print(sb.toString());
	}
	
	private static boolean recursion(int x, int q, int d, int n, int p) {
		if(x < 0) return false;
		if(q > 20 || d > 50 || n > 100 || p > 500) return false;
		
		if(x == 0) {
			int sum = q + d + n + p;	// 동전 수 총합
			
			if(sum < min) {
				result[0] = q;			// 각 동전 갯수 채우기
				result[1] = d;
				result[2] = n;
				result[3] = p;
				
				min = sum;
			}
			
			return true;
		}
		
		if(dp[x]) return dp[x];									// 이미 계산된 카운트

		dp[x] = recursion(x - coins[0], q + 1, d, n, p);		// 각 동전 별 거스름돈 경우의 수
		dp[x] = recursion(x - coins[1], q, d + 1, n, p);
		dp[x] = recursion(x - coins[2], q, d, n + 1, p);
		dp[x] = recursion(x - coins[3], q, d, n, p + 1);
		
		return dp[x];
	}
}
