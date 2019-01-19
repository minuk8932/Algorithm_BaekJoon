package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15664번: N과 M 10
 *
 *	@see https://www.acmicpc.net/problem/15664/
 *
 */
public class Boj15664 {
	private static StringBuilder sb = new StringBuilder();
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static HashSet<String> hs = new HashSet<>();
	
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
	
	private static void backTracking(int n, int m, int[] arr, int depth, int count, String line) {
		if(depth == m) {
			if(!hs.contains(line)) {			// 존재하는 조합이면 출력 안함
				hs.add(line);
				sb.append(line).append(NEW_LINE);
			}
			return;
		}
		
		if(count == n) return;
		
		backTracking(n, m, arr, depth + 1, count + 1, line + arr[count] + SPACE);
		backTracking(n, m, arr, depth, count + 1, line);				// 백트래킹
	}
}
