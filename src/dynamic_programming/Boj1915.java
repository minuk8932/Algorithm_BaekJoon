package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 1915번: 가장 큰 정사각형
 */
public class Boj1915 {
	private static int[][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			String input = br.readLine();
			for(int j = 0; j < m; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		dp = new int[n + 1][m + 1];
		System.out.println(search(n, m, map));
	}
	
	private static int search(int n, int m, int[][] arr) {
		int max = 0;		
		
		for(int i = n - 1; i >= 0; i--) {
			for(int j = m - 1; j >= 0; j--) {
				if(arr[i][j] == 1) {								// state save
					int min = Math.min(dp[i][j + 1], dp[i + 1][j]);
					dp[i][j] = Math.min(dp[i + 1][j + 1], min) + 1;
				}
				
				max = Math.max(dp[i][j], max);
			}
		}
		
		return max * max;
	}
}
