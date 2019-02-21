package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12738번: 가장 긴 증가하는 부분 수열 3
 *
 *	@see https://www.acmicpc.net/problem/12738/
 *
 */
public class Boj12738 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(lis(N, arr));
	}
	
	private static int lis(int n, int[] arr) {
		int[] dp = new int[n];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		dp[0] = arr[0];
		int idx = 0;
		
		for(int i = 1; i < n; i++) {			
			if(dp[idx] < arr[i]) {		// 큰 것은 뒤로 붙여줌
				dp[++idx] = arr[i];
			}
			else {
				int tmp = binarySearch(dp, 0, idx, arr[i]);		// 이분 탐색으로 숫자가 들어갈 위치 탐색
				dp[tmp] = arr[i];
			}
		}
		
		return getArraySize(dp);
	}
	
	private static int binarySearch(int[] dp, int start, int end, int target) {
		int index = 0;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			if(dp[mid] >= target) {
				end = mid - 1;
				index = mid;
			}
			else{
				start = mid + 1;
			}
		}
		
		return index;
	}
	
	private static int getArraySize(int[] arr) {
		int count = 0;
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == Integer.MAX_VALUE) break;
			count++;
		}
		
		return count;
	}
}
