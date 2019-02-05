package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1365번: 꼬인 전깃줄
 *
 *	@see https://www.acmicpc.net/problem/1365/
 *
 */
public class Boj1365 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] wire = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			wire[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(getLis(N, wire));
	}
	
	private static int getLis(int n, int[] arr) {
		int[] dp = new int[n];
		
		int idx = 0;
		dp[0] = arr[0];
		
		for(int i = 1; i < n; i++) {
			if(arr[i] > dp[idx]) {
				dp[++idx] = arr[i];		// 숫자가 이전보다 큰 경우엔 바로 다음 인덱스에 저장
			}
			else {
				int tmp = binarySearch(dp, 0, idx, arr[i]);		// 이분 탐색으로 다음 숫자가 들어갈 자리를 찾음
				dp[tmp] = arr[i];
			}
		}
		
		int length = 0;
		for(int i = 0; i < n; i++) {		// 0보다 큰 수가 존재하는 수 = LIS 크기
			if(dp[i] > 0) length++;
		}
		
		return n - length;
	}
	
	private static int binarySearch(int[] dp, int start, int end, int target) {
		int idx = 0;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			if(dp[mid] >= target) {
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
