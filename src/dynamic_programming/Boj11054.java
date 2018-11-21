package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11054번: 가장 긴 바이토닉 부분 수열
 *
 *	@see https://www.acmicpc.net/problem/11054/
 *
 */
public class Boj11054 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(bottomUp(N, A));		// 결과 출력
	}
	
	private static int bottomUp(int n, int[] arr) {
		int[] dp = new int[n];
		int max = 0;
		
		for(int i = 0; i < n; i++) {
			dp[i] = 1;
		}
		
		dp = dynamicProcess(n, arr, dp, 1);		// 증가하는 부분 수열
		dp = dynamicProcess(n, arr, dp, -1);	// 감소하는 부분 수열
		
		for(int i = 0; i < n; i++) {
			if(max < dp[i]) max = dp[i];
		}
		
		return max;		// 최댓값 반환
	}
	
	private static int[] dynamicProcess(int n, int[] arr, int[] dp, int comp) {
		for(int i = 1; i < n; i++) {
			for(int j = i - 1; j >= 0; j--) {
				int diff = (arr[i] - arr[j]) * comp;
				
				if(diff > 0) {
					if(dp[i] < dp[j] + 1) dp[i] = dp[j] + 1;
				}
			}
		}
		
		return dp;
	}
}
