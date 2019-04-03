package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12354번: Ocean View(small)
 *
 *	@see https://www.acmicpc.net/problem/12354/
 *
 */
public class Boj12354 {
	private static final String CASE = "Case #";
	private static final String COLON = ": ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] home = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				home[i] = Integer.parseInt(st.nextToken());
			}
			
			sb.append(CASE).append(t + 1).append(COLON).append(lis(N, home)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int lis(int n, int[] arr) {
		int index = 0;
		
		int[] dp = new int[n];
		dp[index] = arr[0];
		
		for(int i = 1; i < n; i++) {					// 가장 긴 증가하는 부분수열 길이 = 잔여
			if(arr[i] > dp[index]) {
				dp[++index] = arr[i];
			}
			else {
				int tmp = binarySearch(dp, arr[i], 0, index);
				dp[tmp] = arr[i];
			}
		}
		
		return n - (index + 1);
	}
	
	private static int binarySearch(int[] dp, int target, int start, int end) {
		int idx = 0;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			if(dp[mid] > target) {
				end = mid - 1;
				idx = mid;
			}
			else {
				start = mid + 1;
			}
		}
		
		return idx;
	}
}
