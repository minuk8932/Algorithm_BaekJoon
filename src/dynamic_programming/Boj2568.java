package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2568번: 전깃줄 2
 *
 *	@see https://www.acmicpc.net/problem/2568/
 *
 */
public class Boj2568 {
	private static final String NEW_LINE = "\n";
	
	private static class Pair implements Comparable<Pair>{
		int idx;
		int x;
		int y;
		
		public Pair(int idx, int x, int y) {
			this.idx = idx;
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair p) {
			if(this.x < p.x) return -1;
			else if(this.x > p.x) return 1;
			else return 0;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Pair[] pair = new Pair[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pair[i] = new Pair(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(pair);
		System.out.println(getList(N, pair));
	}
	
	private static StringBuilder getList(int n, Pair[] p) {
		StringBuilder sb = new StringBuilder();
		int[] dp = new int[n];
		Pair[] track = new Pair[n];
		
		int index = 0;
		dp[index] = p[0].y;
		track[0] = new Pair(0, p[0].x, p[0].y);		// lis에 존재하는 값 추적
		
		for(int i = 1; i < n; i++) {				// lis 길이 구하기
			if(dp[index] == p[i].y) continue;
			
			if(dp[index] < p[i].y) {
				dp[++index] = p[i].y;
				
				track[i] = new Pair(index, p[i].x, p[i].y);
			}
			else {
				int tmp = binarySearch(p, dp, 0, index, p[i].y);
				dp[tmp] = p[i].y;
				
				track[i] = new Pair(tmp, p[i].x, p[i].y);
			}
		}
		
		sb.append(n - 1 - index).append(NEW_LINE);
		boolean[] except = new boolean[n];
		
		for(int i = track.length - 1; i >= 0; i--) {
			if(index != track[i].idx) continue;

			except[i] = true;						// lis에 포함되는 값 제외시키기
			index--;
		}

		for(int i = 0; i < n; i++) {
			if(except[i]) continue;
			sb.append(track[i].x).append(NEW_LINE);
		}
		
		return sb;
	}
	
	private static int binarySearch(Pair[] p, int[] dp, int start, int end, int target) {
		int idx = 0;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			if(target > dp[mid]) {
				start = mid + 1;
			}
			else {
				end = mid - 1;
				idx = mid;
			}
		}
		
		return idx;
	}
}
