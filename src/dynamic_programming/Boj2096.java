package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2096번: 내려가기
 *
 *	@see https://www.acmicpc.net/problem/2096/
 *
 */
public class Boj2096 {
	private static final int INF = 10_000_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][3];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(downStair(N, map));
	}
	
	private static StringBuilder downStair(int n, int[][] arr) {
		StringBuilder sb = new StringBuilder();
		int[][][] dp = new int[n][3][2];			// [row][col][min, Max]
		dp[0][0][0] = dp[0][0][1] = arr[0][0];
		dp[0][1][0] = dp[0][1][1] = arr[0][1];
		dp[0][2][0] = dp[0][2][1] = arr[0][2];
		
		for(int i = 1; i < n; i++) {			// 내려가면서 각 위치별 최대 최소를 구함
			dp[i][0][0] = getMinMax(dp[i - 1][0][0], dp[i - 1][1][0], INF, true) + arr[i][0];
			dp[i][1][0] = getMinMax(dp[i - 1][0][0], dp[i - 1][1][0], dp[i - 1][2][0], true) + arr[i][1];
			dp[i][2][0] = getMinMax(dp[i - 1][1][0], dp[i - 1][2][0], INF, true) + arr[i][2];
			
			dp[i][0][1] = getMinMax(dp[i - 1][0][1], dp[i - 1][1][1], 0, false) + arr[i][0];
			dp[i][1][1] = getMinMax(dp[i - 1][0][1], dp[i - 1][1][1], dp[i - 1][2][1], false) + arr[i][1];
			dp[i][2][1] = getMinMax(dp[i - 1][1][1], dp[i - 1][2][1], 0, false) + arr[i][2];
		}
		
		int min = INF, max = 0;
		for(int i = 0; i < 3; i++) {
			if(min > dp[n - 1][i][0]) min = dp[n - 1][i][0];
			if(max < dp[n - 1][i][1]) max = dp[n - 1][i][1];
		}
		
		return sb.append(max).append(" ").append(min);
	}
	
	private static int getMinMax(int a, int b, int c, boolean flag) {
		if(flag) return Math.min(a, Math.min(b, c));
		else return Math.max(a, Math.max(b, c));
	}
}
