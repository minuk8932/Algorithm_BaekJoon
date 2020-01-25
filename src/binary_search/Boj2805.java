package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2805번: 나무 자르기
 *
 *	@see https://www.acmicpc.net/problem/2805/
 *
 */
public class Boj2805 {
	private static final long INF =  4_000_000_001L;
	private static long end = INF;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		long[] tree = new long[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			tree[i] = Long.parseLong(st.nextToken());
			if(end < tree[i]) end = tree[i];
		}
		
		System.out.println(binarySearch(N, M, tree));
	}
	
	private static long binarySearch(int n, long m, long[] arr) {
		long high = 0;
		long start = 0;
		
		while(start <= end) {
			long mid = (start + end) / 2;
			long sum = 0;
			
			for(int i = 0; i < n; i++) {
				long diff = arr[i] - mid;		// cut
				if(diff < 0) diff = 0;
				
				sum += diff;					// make get sum
			}
			
			if(m <= sum) {
				high = Math.max(high, mid);
				start = mid + 1;
			}
			else {
				end = mid - 1;
			}
		}
		
		return high;
	}
}
