import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1915 {
	private static int[][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n + 1][m + 1];
		for(int i = 1; i < n + 1; i++) {
			String input = br.readLine();
			for(int j = 1; j < m + 1; j++) {
				map[i][j] = input.charAt(j - 1) - '0';
			}
		}
		
		dp = new int[n + 1][m + 1];
		System.out.println(recursion(n, m, map));
	}
	
	private static int recursion(int row, int col, int[][] arr) {
		if(row == 0 || col == 0) return 0;
		if(dp[row][col] != 0) return dp[row][col];
		
		if(arr[row][col] == 1) {
			dp[row][col] = Math.max(dp[row][col] + 1, recursion(row - 1, col, arr) + 1);
			dp[row][col] = Math.max(dp[row][col] + 1, recursion(row, col - 1, arr) + 1);
			dp[row][col] = Math.max(dp[row][col] + 1, recursion(row - 1, col - 1, arr) + 1);
		}
		else {
			for(int i = row; i > 0; i--) {
				for(int j = col; j > 0; j--) {
					if(arr[i][j] == 1) dp[row][col] = Math.max(recursion(i, j, arr), dp[row][col]) + 1;
				}
			}
		}
		
		return dp[row][col];
	}
}
