package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 3079번: 입국심사
 *
 *	@see https://www.acmicpc.net/problem/3079/
 *
 */
public class Boj3079 {
	private static long INF = 1_000_000_000_000_000_000L;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		long[] time = new long[N];
		for(int i = 0; i < N; i++) {
			time[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(binarySearch(N, M, 0, INF, time));
	}
	
	private static long binarySearch(int n, long m, long start, long end, long[] arr) {
		long result = INF;
		
		while(start <= end) {
			long mid = (start + end) / 2;
			long count = passer(m, arr, mid);
			
			if(count >= m) {		// all friends covered
				end = mid - 1;
				if(mid < result) result = mid;
			}
			else {
				start = mid + 1;
			}
		}
		
		return result;
	}
	
	private static long passer(long m, long[] arr, long target) {
		long cnt = 0;
		
		for(int i = 0; i < arr.length; i++) {		// the number of people per time
			cnt += (target / arr[i]);
			if(cnt >= m) cnt = m;					// overflow safe
		}
		
		return cnt;
	}
}
