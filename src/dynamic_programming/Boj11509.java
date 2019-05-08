package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11509번: 풍선 맞추기
 *
 *	@see https://www.acmicpc.net/problem/11509/
 *
 */
public class Boj11509 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] balloon = new int[N];
		int max = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			balloon[i] = Integer.parseInt(st.nextToken());
			if(balloon[i] > max) max = balloon[i];
		}
		
		System.out.println(getResult(N, balloon, max));
	}
	
	private static int getResult(int n, int[] arr, int size) {
		int[] dp = new int[size + 2];
		
		for(int i = 0; i < n; i++) {
			if(dp[arr[i] + 1] > 0) {			// 이전에 i보다 높이가 1 높은 풍선을 터트린 경우
				dp[arr[i] + 1]--;
				dp[arr[i]]++;
			}
			else {								// 화살이 하나 더 필요한 경우 (연속된 높이가 아님)
				dp[arr[i]]++;
			}
		}
		
		
		int count = 0;
		for(int i = 0; i < dp.length; i++) {		// 누적된 화살 갯수 합
			count += dp[i];
		}
		
		return count;
	}
}
