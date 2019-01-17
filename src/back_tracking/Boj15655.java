package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15655번: N과 M 6
 *
 *	@see https://www.acmicpc.net/problem/15655/
 *
 */
public class Boj15655 {
	private static StringBuilder sb = new StringBuilder();
	private static int[] save;
	
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] seq = new int[N];
		save = new int[M];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(seq);
		backTracking(seq, N, M, 0, 0);
		
		System.out.println(sb);
	}
	
	private static void backTracking(int[] arr, int n, int m, int depth, int idx) {
		if(depth == m) {
			for(int i = 0; i < save.length; i++) {
				if(save[i] != 0) {
					sb.append(save[i]).append(SPACE);
				}
			}
			sb.append(NEW_LINE);
			
			return;
		}
		
		if(n == idx) return;
		
		save[depth] = arr[idx];
		backTracking(arr, n, m, depth + 1, idx + 1);
		save[depth] = 0;							// 백트래킹, 리프 노드 숫자 제거
		backTracking(arr, n, m, depth, idx + 1);	// 옆 리프 노드로 이동
	}
}
