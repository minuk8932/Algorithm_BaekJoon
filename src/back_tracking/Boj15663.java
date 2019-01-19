package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15663번: N과 M 9
 *
 *	@see https://www.acmicpc.net/problem/15663/
 *
 */
public class Boj15663 {
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	private static StringBuilder sb = new StringBuilder();

	private static boolean[] isVisited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] seq = new int[N];
		isVisited = new boolean[10_001];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(seq);
		
		backTracking(N, M, seq, 0, "");
		System.out.println(sb);
	}
	
	private static void backTracking(int n, int m, int[] arr, int depth, String line) {
		if(m == depth) {
			sb.append(line).append(NEW_LINE);
			return;
		}
		
		int before = 0;
		
		for(int current = 0; current < arr.length; current++) {
			if(isVisited[current] || before == arr[current]) continue;
			isVisited[current] = true;
			before = arr[current];			// 이전 것과 중복되면 제거
			
			backTracking(n, m, arr, depth + 1, line + arr[current] + SPACE);
			isVisited[current] = false;		// 백트래킹
		}
	}
}
