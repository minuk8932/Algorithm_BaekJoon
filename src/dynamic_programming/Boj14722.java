package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14722번: 우유도시
 *
 *	@see https://www.acmicpc.net/problem/14722/
 *
 */
public class Boj14722 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] milk = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				milk[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(drink(N, milk));
	}
	
	private static int drink(int n, int[][] arr) {
		int[][][] dp = new int[3][n][n];
		dp[arr[0][0]][0][0] = arr[0][0] == 0 ? 1 : 0;
		
		for(int col = 1; col < n; col++) {				// 가장 위쪽 상점 방문
			int milk = arr[0][col];
			
			dp[0][0][col] = milk == 0 ? dp[2][0][col - 1] + 1: dp[0][0][col - 1];
			dp[1][0][col] = milk == 1 && dp[2][0][col] < dp[0][0][col] ? dp[0][0][col - 1] + 1: dp[1][0][col - 1];
			dp[2][0][col] = milk == 2 && dp[0][0][col] < dp[1][0][col] ? dp[1][0][col - 1] + 1: dp[2][0][col - 1];
		}
		
		for(int row = 1; row < n; row++) {				// 가장 왼쪽 상점 방문
			int milk = arr[row][0];
			
			dp[0][row][0] = milk == 0 ? dp[2][row - 1][0] + 1: dp[0][row - 1][0];
			dp[1][row][0] = milk == 1 && dp[2][row][0] < dp[0][row][0] ? dp[0][row - 1][0] + 1: dp[1][row - 1][0];
			dp[2][row][0] = milk == 2 && dp[0][row][0] < dp[1][row][0] ? dp[1][row - 1][0] + 1: dp[2][row - 1][0];
		}
		
		for(int row = 1; row < n; row++) {
			for(int col = 1; col < n; col++) {			// 오른쪽, 아래로 움직이며 방문 가능한 상점 탐색
				int milk = arr[row][col];
				
				if(milk == 0) 
					dp[0][row][col] = Math.max(dp[2][row - 1][col], dp[2][row][col - 1]) + 1;
				else 
					dp[0][row][col] = Math.max(dp[0][row - 1][col], dp[0][row][col - 1]);
				
				if(milk == 1 && dp[2][row][col] < dp[0][row][col]) 
					dp[1][row][col] = Math.max(dp[0][row - 1][col], dp[0][row][col - 1]) + 1;
				else 
					dp[1][row][col] = Math.max(dp[1][row - 1][col], dp[1][row][col - 1]);
				
				if(milk == 2 && dp[0][row][col] < dp[1][row][col]) 
					dp[2][row][col] = Math.max(dp[1][row - 1][col], dp[1][row][col - 1]) + 1;
				else 
					dp[2][row][col] = Math.max(dp[2][row - 1][col], dp[2][row][col - 1]);
					
			}
		}
		
		return getMax(dp[0][n - 1][n - 1], dp[1][n - 1][n - 1], dp[2][n - 1][n - 1]);
	}
	
	private static int getMax(int a, int b, int c) {
		return Math.max(a, Math.max(b, c));
	}
}
