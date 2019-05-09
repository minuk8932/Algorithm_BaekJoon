package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12785번: 토쟁이의 등굣길
 *
 *	@see https://www.acmicpc.net/problem/12785/
 *
 */
public class Boj12785 {
	private static final int MOD = 1_000_007;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken()) - 1;
		int y = Integer.parseInt(st.nextToken()) - 1;
		
		long toast = getPath(0, 0, y + 1, x + 1);		// 토스트 집 까지
		long school = getPath(y, x, h, w);				// 학교 까지
		
		System.out.println((toast * school) % MOD);		// 총 경우의 수
	}
	
	private static long getPath(int x, int y, int h, int w) {
		long[][] dp = new long[h][w];
		
		for(int i = x; i < h; i++) {
			dp[i][y] = 1;
		}
		
		for(int j = y; j < w; j++) {
			dp[x][j] = 1;
		}
		
		for(int i = x + 1; i < h; i++) {
			for(int j = y + 1; j < w; j++) {
				dp[i][j] = ((dp[i - 1][j] % MOD) + (dp[i][j - 1] % MOD)) % MOD;
			}
		}
		
		return dp[h - 1][w - 1] % MOD;
	}
}
