package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 18113번: 그르다 김가놈
 *
 *	@see https://www.acmicpc.net/problem/18113/
 *
 */
public class Boj18113 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] L = new int[N];
		for(int i = 0; i < N; i++) {
			L[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(minLen(N, K, M, L));
	}
	
	private static int minLen(int n, long k, int m, int[] arr) {
		int res = -1;
		int start = 1, end = 1_000_000_000;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			long count = 0;
			
			for(int i = 0; i < n; i++) {
				if(arr[i] <= k) continue;
				long k2 = 2 * k;
				long val = 0;
				
				if(arr[i] < k2) val = arr[i] - k;		// remove tail
				else val = arr[i] - k2;
				
				count += (val / mid);					// count what is sufficient
			}
			
			if(count >= m) {
				start = mid + 1;
				res = Math.max(res, mid);
			}
			else {
				end = mid - 1;
			}
		}
		
		return res;
	}
}
