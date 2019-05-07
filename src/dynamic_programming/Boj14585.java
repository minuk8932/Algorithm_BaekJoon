package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14585번: 사수빈탕
 *
 *	@see https://www.acmicpc.net/problem/14585/
 *
 */
public class Boj14585 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] bucket = new int[301][301];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int remain = M - (x + y);				// 해당 위치로 최적의 거리로 갔을 때 남아있는 사탕의 수
			
			bucket[x][y] = remain <= 0 ? 0: remain;
		}
		
		System.out.println(getCandies(N, bucket));
	}
	
	private static int getCandies(int n, int[][] candy) {
		int[][] dp = new int[301][301];
		
		for(int i = 1; i < 301; i++) {
			dp[0][i] = dp[0][i - 1] + candy[0][i];
		}
		
		for(int i = 1; i < 301; i++) {
			dp[i][0] = dp[i - 1][0] + candy[i][0];
		}
		
		for(int i = 1; i < 301; i++) {
			for(int j = 1; j < 301; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + candy[i][j];		// 현재 바구니의 사탕 수 + 이전까지 먹을 수 있는 사탕의 최대 값
			}
		}
		
		return dp[300][300];
	}
}
