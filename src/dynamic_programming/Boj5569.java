package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 5569번: 출근 경로
 *
 *	@see https://www.acmicpc.net/problem/5569/
 *
 */
public class Boj5569 {
	private static final int MOD = 100_000;
	private static int[][][] dp;
	
	private static int w, h;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		dp = new int[h][w][4];
		System.out.println(getPath(h - 1, w - 1));
	}
	
	private static int getPath(int n, int m) {
		for(int i = 0; i < h; i++) {
			dp[i][0][1] = 1;
		}
		
		for(int i = 0; i < w; i++) {
			dp[0][i][3] = 1;
		}
		
		for(int i = 1; i < h; i++) {
			for(int j = 1; j < w; j++) {
				dp[i][j][0] = dp[i - 1][j][3];			// 한번 꺽었으니 그냥 지나가는 경로
				dp[i][j][2] = dp[i][j - 1][1];
				
				dp[i][j][1] = ((dp[i - 1][j][0] % MOD) + (dp[i - 1][j][1] % MOD)) % MOD;		// 경로의 경우의 수를 합치기
				dp[i][j][3] = ((dp[i][j - 1][2] % MOD) + (dp[i][j - 1][3] % MOD)) % MOD;
			}
		}
		
		int total = 0;
		for(int i = 0; i < 4; i++) {
			total = ((total % MOD) + (dp[n][m][i] % MOD)) % MOD;
		}
		
		return total;
	}
}
