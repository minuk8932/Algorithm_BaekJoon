package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15654번: N과 M 5
 *
 *	@see https://www.acmicpc.net/problem/15654/
 *
 */
public class Boj15654 {
	private static StringBuilder sb = new StringBuilder();
	private static int[] save;
	private static boolean[] isVisited;
	
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] seq = new int[N];
		isVisited = new boolean[N];
		save = new int[M];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(seq);
		backTracking(seq, N, M, 0);
		
		System.out.println(sb);
	}
	
	private static void backTracking(int[] arr, int n, int m, int depth) {
		if(depth == m) {
			for(int i = 0; i < save.length; i++) {
				if(save[i] != 0) {
					sb.append(save[i]).append(SPACE);
				}
			}
			sb.append(NEW_LINE);
			
			return;
		}
		
		for(int next = 0; next < n; next++) {
			if(isVisited[next]) continue;
			isVisited[next] = true;			// 중복 제거
			
			save[depth] = arr[next];
			backTracking(arr, n, m, depth + 1);
			isVisited[next] = false;			// 백트래킹
		}
	}
}
