package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15810번: 풍선 공장
 *
 *	@see https://www.acmicpc.net/problem/15810/
 *
 */
public class Boj15810 {
	private static final long INF = 1_000_000_000_001L;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] time = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			time[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(binarySearch(N, M, time));
	}
	
	private static long binarySearch(int n, long target, int[] make) {
		long time = 0;
		long start = 0, end = INF;
		
		while(start <= end) {
			long mid = (start + end) / 2;
			long sum = 0;
			
			for(int i = 0; i < n; i++) {	// 기준 시간당 각 직원이 만들 수 있는 풍선의 갯수
				sum += (mid / make[i]);
			}
			
			if(sum >= target) {				// 해당 시간으로 목적 풍선 갯수 이상으로 만들 수 있는 경우
				end = mid - 1;
				time = mid;
			}
			else {
				start = mid + 1;
			}
		}
		
		return time;
	}
}
