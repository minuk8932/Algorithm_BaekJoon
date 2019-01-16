package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15652번: N과 M 4
 *
 *	@see https://www.acmicpc.net/problem/15651/
 *
 */
public class Boj15652 {
	private static int[] nums;
	private static StringBuilder sb = new StringBuilder();
	
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		backTracking(N, M, 0, 0);	
		
		System.out.println(sb);
	}
	
	private static void backTracking(int n, int m, int depth, int current) {		
		if(depth == m) {
			for(int i = 0; i < n; i++) {
				if(nums[i] == 0) continue;
				sb.append(nums[i]).append(SPACE);
			}
			
			sb.append(NEW_LINE);
			return;
		}
		
		if(current == n) return;
		
		nums[depth] = current + 1;
		backTracking(n, m, depth + 1, current++);
		backTracking(n, m, depth, current);			// 리턴되는 동시에 시작 숫자 + 1 후 백트래킹
	}
}
