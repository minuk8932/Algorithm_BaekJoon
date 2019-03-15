package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2631번: 줄 세우기
 *
 *	@see https://www.acmicpc.net/problem/2631/
 *
 */
public class Boj2631 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] que = new int[N];
		for(int i = 0; i < N; i++) {
			que[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(lis(N, que));
	}
	
	private static int lis (int n, int[] arr) {			// least increment subsequence
		int[] dp = new int[n];
		dp[0] = arr[0];
		
		int index = 0;
		
		for(int i = 1; i < n; i++) {
			if(dp[index] == arr[i]) continue;
			
			if(dp[index] < arr[i]) {
				dp[++index] = arr[i];
			}
			else {
				int tmp = binarySearch(dp, 0, index, arr[i]);		// 이분 탐색으로 다음 숫자(target)가 들어갈 위치를 찾는다
				dp[tmp] = arr[i];
			}
		}
		
		return (n - 1) - index;
	}
	
	private static int binarySearch(int[] dp, int start, int end, int target) {
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
