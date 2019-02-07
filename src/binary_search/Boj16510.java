package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16510번: Predictable Queue
 *
 *	@see https://www.acmicpc.net/problem/16510/
 *
 */
public class Boj16510 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] works = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N + 1; i++) {
			works[i] = Integer.parseInt(st.nextToken()) + works[i - 1];		// 진행 시간 별 작업 시간 축적
		}
		
		while(M-- > 0) {
			sb.append(binarySearch(N, works, Integer.parseInt(br.readLine()))).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int binarySearch(int n, int[] q, int target) {
		int idx = 0;
		int start = 1, end = n;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			if(q[mid] > target) {
				end = mid - 1;
			}
			else {
				start = mid + 1;
				idx = mid;
			}
		}
		
		return idx;
	}
}
