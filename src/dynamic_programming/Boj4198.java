package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 4198번: 열차 정렬
 *
 *	@see https://www.acmicpc.net/problem/4198/
 *
 */
public class Boj4198 {
	private static int[][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] trains = new int[N];
		for(int i = 0; i < N; i++) {
			trains[i] = Integer.parseInt(br.readLine());
		}
		
		dp = new int[2][N];
		System.out.println(getResult(N, trains));
	}
	
	private static int getResult (int n, int[] arr) {
		int result = 0;
		if(n == 0) return result;
		
		lis(n, arr);									// 바이토닉 부분 수열 활용
		lds(n, arr);
		
		for(int i = 0; i < n; i++) {
			result = Math.max(dp[0][i] + dp[1][i] - 1, result);		// 각 경우에서 최장 길이
		}
		
		return result;
	}
	
	private static void lis(int n, int[] arr) {		
		Arrays.fill(dp[0], 1);
			
		for(int i = n - 1; i >= 0; i--) {
			for(int j = n - 1; j > i; j--) {
				if(arr[i] < arr[j]) {					// j의 값이 큰 경우만 +1, 기준점이 필요하므로 작은 경우는 배제함
					if(dp[0][i] < dp[0][j] + 1) dp[0][i] = dp[0][j] + 1;
				}
			}
		}
	}
	
	private static void lds(int n, int[] arr) {
		Arrays.fill(dp[1], 1);
		
		for(int i = n - 1; i >= 0; i--) {
			for(int j = n - 1; j > i; j--) {
				if(arr[i] > arr[j]) {
					if(dp[1][i] < dp[1][j] + 1) dp[1][i] = dp[1][j] + 1;
				}
			}
		}
	}
}
