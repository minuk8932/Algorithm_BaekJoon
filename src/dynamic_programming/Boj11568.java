package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11568번: 민균이의 계략
 *
 *	@see https://www.acmicpc.net/problem/11568/
 *
 */
public class Boj11568 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(LIS(N, arr));
	}
	
	private static int LIS(int n, int[] arr) {
		int[] dp = new int[n];
		int seqLength = 0;
		
		dp[seqLength] = arr[0];
		
		for(int i = 1; i < n; i++) {
			if(arr[i] == dp[seqLength]) continue;
			
			if(arr[i] < dp[seqLength]) {
				int tmp = binarySearch(dp, 0, seqLength, arr[i]);		// 다음 숫자가 작다면 들어갈 자리를 찾음
				dp[tmp] = arr[i];
			}
			else {								// 큰 경우
				dp[++seqLength] = arr[i];
			}
		}
		
		return seqLength + 1;
	}
	
	private static int binarySearch(int[] dp, int left, int right, int target) {
		int idx = 0;
		
		while(left <= right) {				// 이분 탐색으로 위치 지정
			int mid = (left + right) / 2;
			
			if(dp[mid] >= target) {
				right = mid - 1;
				idx = mid;
			}
			else {
				left = mid + 1;
			}
		}

		return idx;
	}
}
