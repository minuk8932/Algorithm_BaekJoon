package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15666번: N과 M 12
 *
 *	@see https://www.acmicpc.net/problem/15666/
 *
 */
public class Boj15666 {
	private static final String NEW_LINE = "\n", SPACE = " ";
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] seq = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(seq);
		
		backTracking(N, M, seq, 0, 0, "");
		System.out.println(sb);
	}
		
	private static void backTracking(int n, int m, int[] arr, int depth, int start, String str) {
		if(m == depth) {
			sb.append(str).append(NEW_LINE);
			return;
		}
	        
        if(start == n) return;
			
		int before = 0;
			
		for(int current = start; current < n; current++) {		// 이전 다음 인덱스부터
			if(before == arr[current]) continue;				// 이전과 같은것 제외
			before = arr[current];
				
			backTracking(n, m, arr, depth + 1, current, str + arr[current] + SPACE);		// 백트래킹
		}
	}
}
