package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15650번: N 과 M 2
 *
 *	@see https://www.acmicpc.net/problem/15650/
 *
 */
public class Boj15650 {
	private static StringBuilder sb = new StringBuilder();
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static int[] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		br.close();
		
		arr = new int[N + 1];
		backTracking(N, M, 1, 0);
		
		System.out.println(sb);
	}
	
	private static void backTracking(int n, int m, int start, int count) {		
		if(count == m) {
			for(int i = 1; i < arr.length; i++) {				// 다 찾았네!
				if(arr[i] != 0) sb.append(arr[i]).append(SPACE);
			}
			sb.append(NEW_LINE);
			return;	
		}
		
		if(start > n) return;
		
		arr[start] = start;
		backTracking(n, m,  start + 1, count + 1);
		arr[start] = 0;								// 백트래킹
		backTracking(n, m, start + 1, count);
	}
}
