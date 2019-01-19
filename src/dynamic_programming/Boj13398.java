package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 * 백준 13398번: 연속합 2
 * 
 * @see https://www.acmicpc.net/problem/13398/
 *
 */
public class Boj13398 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(getMaxValue(N, arr));
	}
	
	private static int getMaxValue(int N, int[] arr) {		
		if(N == 1) return arr[0];		// 원소가 한개인 경우
		
		int[][] dp = new int[N][2];
		int max = arr[0];
		dp[0][1] = arr[0];
		
		for(int i = 1; i < N; i++) {
			dp[i][0] = Math.max(dp[i - 1][0] + arr[i], dp[i - 1][1]);	// (한개 제외 + 현재)와 현재 제외 최대
			dp[i][1] = Math.max(dp[i - 1][1] + arr[i], arr[i]);			// 제외 하지않은 것과 현재의 최대
			
			max = getMax(dp[i][0], dp[i][1], max);		// 부분 최대 합
		}
		
		return max;
	}
	
	private static int getMax(int a, int b, int c) {
		int tmp = Math.max(a, b);
		return tmp > c ? tmp : c;
	}
}
