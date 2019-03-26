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
	
	private static long binarySearch(int n, int m, long start, long end, long[] arr) {
		long result = 0;
		
		while(start <= end) {
			long mid = (start + end) / 2;
			long count = passer(n, arr, mid);
			
			if(count >= m) {		// 해당 인원 수 만큼 심사 받는 경우
				end = mid - 1;
				result = mid;
			}
			else {
				start = mid + 1;
			}
		}
		
		return result;
	}
	
	private static long passer(int n, long[] arr, long target) {
		long cnt = 0;
		
		for(int i = 0; i < n; i++) {		// 목적 시간당 심사를 받는 인원 수
			cnt += (target / arr[i]);
		}
		
		return cnt;
	}
}
