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
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		dp = new int[n + 1][m + 1];
		System.out.println(recursion(n, m, map));
	}
	
	private static int recursion(int row, int col, int[][] arr) {
		if(row == 0 || col == 0) return 0;
		if(dp[row][col] != 0) return dp[row][col];
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				if(arr[i][j] == 0) {
					
				}
			}
		}
		
		return dp[row][col] = dp[row][col] + 1;
	}
}
