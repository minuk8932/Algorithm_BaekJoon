package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14494번: 다이나믹이 뭐예요?
 *
 *	@see https://www.acmicpc.net/problem/14494/
 *
 */
public class Boj14494 {
	private static final int MOD = 1_000_000_007;
	private static final int INF = 1_001;
	
	private static long[][] dp = new long[INF][INF];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		init();
		
		System.out.println(getCase(n, m));
	}
	
	private static int getCase(int n, int m) {		
		for(int i = 2; i < INF; i++) {
			for(int j = 2; j < INF; j++) {
				dp[i][j] = (dp[i - 1][j] % MOD) + (dp[i][j - 1] % MOD) + (dp[i - 1][j - 1] % MOD);		// 이동 경로 가짓 수
			}
		}
		
		return (int) (dp[n][m] % MOD);
	}
	
	private static void init() {
		for(int i = 1; i < INF; i++) {
			dp[i][1] = dp[1][i] = 1;
		}
	}
}
