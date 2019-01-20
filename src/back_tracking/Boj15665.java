package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15665번: N과 M 11
 *
 *	@see https://www.acmicpc.net/problem/15665/
 *
 */
public class Boj15665 {
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
		
		backTracking(N, M, seq, 0, "");
		System.out.println(sb);
	}
	
	private static void backTracking(int n, int m, int[] arr, int depth, String str) {
		if(depth == m) {
			sb.append(str).append(NEW_LINE);
			
			return;
		}
		
		int before = 0;
		for(int current = 0; current < n; current++) {
			if(before == arr[current]) continue;			// 이전에 담은 값과 같다면 다음 숫자
			before = arr[current];
			
			backTracking(n, m, arr, depth + 1, str + arr[current] + SPACE);		// 재귀를 돌다 버퍼에 저장하면 이전 번째 다음 숫자로 백트래킹
		}
	}
}
