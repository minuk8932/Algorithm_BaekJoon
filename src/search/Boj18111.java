package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 * 	백준 18111번: 마인 크래프트
 *
 *	@see https://www.acmicpc.net/problem/18111/
 *
 */
public class Boj18111 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		int[][] map = new int[N][M];
		int min = 300;
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < M; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
				min = Math.min(map[i][j], min);
				max = Math.max(map[i][j], max);
			}
		}

		System.out.println(search(N, M, B, map, min, max));
	}
	
	private static String search(int n, int m, long b, int[][] arr, int start, int end) {
		long result = Long.MAX_VALUE;
		int high = 0;
		
		for(int hi = end; hi >= start; hi--) {				// search height
			long timer = 0;
			long total = b;

			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					long diff = hi - arr[i][j];
					if(diff == 0) continue;
					
					timer += (diff > 0 ? diff: -2 * diff);	// add or remove
					total -= diff;
				}
			}
			
			if(total >= 0 && timer < result) {				// min time, max height
				result = timer;
				high = hi;
			}
		}
		
		StringBuilder sb = new StringBuilder();		
		return sb.append(result).append(SPACE).append(high).toString();
	}
}
