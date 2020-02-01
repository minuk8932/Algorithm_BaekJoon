package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 18353번: 병사 배치하기
 *
 *	@see https://www.acmicpc.net/problem/18353/
 *
 */
public class Boj18353 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] soldier = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = N - 1; i >= 0; i--) {
			soldier[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(lis(N, soldier));
	}
	
	private static int lis(int n, int[] arr) {
		int[] dp = new int[n];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		dp[0] = arr[0];
		int idx = 0;
		
		for(int i = 1; i < n; i++) {						// lis
			if(dp[idx] < arr[i]) {
				dp[++idx] = arr[i];
			}
			else {
				int tmp = binarySearch(dp, 0, idx, arr[i]);
				dp[tmp] = arr[i];
			}
		}
		
		return n - getArraySize(dp);						// get removed
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
