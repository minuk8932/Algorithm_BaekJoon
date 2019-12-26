package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 7570번: 줄세우기
 *
 *	@see https://www.acmicpc.net/problem/7570/
 *
 */
public class Boj7570 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] child = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			child[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(lis(N, child));
	}
	
	private static int lis(int n, int[] arr) {
		int max = 1;
		int[] dp = new int[n + 1];
		
		for(int i = 0; i < n; i++) {
			dp[arr[i]] = dp[arr[i] - 1] + 1;			// find lis & sequence
			if(dp[arr[i]] > max) max = dp[arr[i]];
		}
		
		return n - max;
	}
}
