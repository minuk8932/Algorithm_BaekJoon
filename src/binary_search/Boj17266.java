package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17266번: 어두운 굴다리
 *
 *	@see https://www.acmicpc.net/problem/17266/
 *
 */
public class Boj17266 {
	private static class Pair{
		int from;
		int to;
		
		public Pair(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] lamp = new int[M];
		int max = 0, prev = 0;
		
		for(int i = 0; i < M; i++) {
			lamp[i] = Integer.parseInt(st.nextToken());
			
			int diff = lamp[i] - prev;
			if(diff > max) max = diff;			// 가능한 최대 높이
			
			prev = lamp[i];
		}
		
		System.out.println(binarySearch(N, M, lamp, max = N - prev > max ? N - prev: max));
	}
	
	private static int binarySearch(int n, int m, int[] arr, int end) {
		int result = 0, start = 0;

		while(start <= end) {
			int mid = (start + end) / 2;
			Pair[] covered = new Pair[m];
			
			for(int i = 0; i < m; i++) {							// 해당 높이(mid)로 커버되는 범위를 저장
				covered[i] = new Pair(arr[i] - mid, arr[i] + mid);
			}
			
			if(isBrightness(n, covered)) {							// 굴 전체가 밝아졌는가?
				end = mid - 1;
				result = mid;
			}
			else {
				start = mid + 1;
			}
		}
		
		return result;
	}
	
	private static boolean isBrightness(int n, Pair[] arr) {
		if(n > arr[arr.length - 1].to || 0 < arr[0].from) return false;
		int prev = 0, post = arr[0].from;
		
		for(int i = 0; i < arr.length - 1; i++) {
			if(post > prev) return false;
			
			prev = arr[i].to;
			post = arr[i + 1].from;
		}
		
		return true;
	}
}
