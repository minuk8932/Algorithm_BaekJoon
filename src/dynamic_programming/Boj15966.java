package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 15966번: 군계일학
 *
 *	@see https://www.acmicpc.net/problem/15966/
 *
 */
public class Boj15966 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(getLength(N, arr));
	}
	
	private static int getLength(int n, int[] arr) {
		int[] dp = new int[1_000_001];
		
		for(int i = 0; i < n; i++) {
			dp[arr[i]] = dp[arr[i] - 1] + 1;
		}
		
		int max = 0;
		for(int i = 0; i < dp.length; i++) {
			if(dp[i] > max) max = dp[i];
		}
		
		return max;
	}
}
