package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2565번: 전깃줄
 *
 *	@see https://www.acmicpc.net/problem/2565/
 *
 */
public class Boj2565 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Pair[] p = new Pair[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			p[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(p);					// x기준 y정렬
		System.out.println(LIS(N, p));
	}
	
	private static class Pair implements Comparable<Pair>{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair p) {
			return this.x < p.x ? -1 : 1;
		}
	}
	
	private static int LIS(int n, Pair[] arr) {		
		int[] dp = new int[n];
		int seqLength = 0;
		
		dp[seqLength] = arr[0].y;
		
		for(int i = 1; i < n; i++) {
			if(arr[i].y <= dp[seqLength]) {
				int tmp = binarySearch(dp, 0, seqLength, arr[i].y);		// 현재 값이 저장된 마지막 값보다 작은 경우 현재 값의 위치를 이분탐색으로 찾아줌
				dp[tmp] = arr[i].y;
			}
			else {
				dp[++seqLength] = arr[i].y;
			}
		}
		
		return n - (seqLength + 1);		// 없애야하는 전깃줄의 갯수
	}
	
	private static int binarySearch(int[] dp, int left, int right, int target) {
		int idx = 0;
		
		while(left <= right) {
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
