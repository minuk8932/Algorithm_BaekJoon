package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16395번: 파스칼의 삼각형
 *
 *	@see https://www.acmicpc.net/problem/16395/
 *
 */
public class Boj16395 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		System.out.println(combination(n, k));
	}
	
	private static int combination(int n, int r) {		// 조합 공식을 DP로 풀어내기
		int[][] dp = new int[n + 1][r + 1];
		dp[1][1] = 1;
		
		for(int i = 2; i < n + 1; i++) {
			for(int j = 1; j < r + 1; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
			}
		}
		
		return dp[n][r];
	}
}
