package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17485번: 진우의 달 여행(large)
 *
 *	@see https://www.acmicpc.net/problem/17485
 *
 */
public class Boj17485 {
	private static final int[] D = {-1, 0, 1};
	private static final long INF = 1_000_000_001;
	
	private static long[][][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N + 1][M + 1];
		dp = new long[N + 1][M + 1][3];
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j < M + 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(path(N, M, map));
	}
	
	private static long path(int n, int m, int[][] arr) {		
		for(int row = 1; row < n + 1; row++) {
			for(int col = 1; col < m + 1; col++) {
				for(int k = 0; k < 3; k++){
					dp[row][col][k] = INF;
					
					for(int l = 0; l < 3; l++){
						int nextCol = col + D[l];
						
						if(k == l || nextCol <= 0 || nextCol > m) continue;						// except same direction
						dp[row][col][k] = Math.min(dp[row - 1][nextCol][l], dp[row][col][k]);
					}
					
					dp[row][col][k] += arr[row][col];
				}
			}
		}
		
		long min = INF;
		for(int i = 1; i < m + 1; i++) {
			for(int j = 0; j < 3; j++) {
				if(min > dp[n][i][j]) min = dp[n][i][j];
			}
		}
		
		return min;
	}
}
