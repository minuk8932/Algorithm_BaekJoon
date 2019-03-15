package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16564번: 히오스 프로게이머
 *
 *	@see https://www.acmicpc.net/problem/16564/
 *
 */
public class Boj16564 {
	private static int INF = 1_000_000_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		long[] champ = new long[N];
		long min = INF;
		
		for(int i = 0; i < N; i++) {
			champ[i] = Integer.parseInt(br.readLine());
			if(champ[i] < min) min = champ[i];
		}
		
		System.out.println(binarySearch(N, K, champ, min));
	}
	
	private static long binarySearch(int n, int k, long[] arr, long start) {
		long level = 0, end = INF;
		
		while(start <= end) {
			long mid = (start + end) / 2;
			
			long sum = 0;
			for(int i = 0; i < n; i++) {					// (어떤 기준값 - 점수)의 총합 -> 이 값이 k의 범위안에 들어가는가?
				if(mid >= arr[i]) sum += (mid - arr[i]);
			}
			
			if(k >= sum) {									// 범위 내라면 분배했을때 가능한 점수의 최댓값!
				start = mid + 1;
				if(level < mid) level = mid;
			}
			else {
				end = mid - 1;
			}
		}
		
		return level;
	}
}
