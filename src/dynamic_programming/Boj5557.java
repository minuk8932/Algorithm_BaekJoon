package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5557번: 1학년
 *
 *	@see https://www.acmicpc.net/problem/5557/
 *
 */
public class Boj5557 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] nums = new int[N];
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(getCount(N, nums));
	}
	
	private static long getCount(int n, int[] arr) {
		long[][] dp = new long[n - 1][21];				// [][0 <= 결과 <= 20]		
		dp[0][arr[0]] = 1;
		
		for(int i = 1; i < n - 1; i++) {
			for(int j = 0; j <= 20; j++) {
				if(j + arr[i] <= 20) dp[i][j + arr[i]] += dp[i - 1][j];		// j + arr[i]: 이전까지의 값 + i번째
				if(j - arr[i] >= 0) dp[i][j - arr[i]] += dp[i - 1][j];		// j - arr[i]: 이전까지의 값 - i번째
			}
		}
		
		return dp[n - 2][arr[n - 1]];			// arr[n - 1] == n - 2까지의 값 경우의 수?
	}
}
