package prefix_sum;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17390번: 이건 꼭 풀어야 해!
 *
 *	@see https://www.acmicpc.net/problem/17390/
 *
 */
public class Boj17390 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		int[] seq = new int[N];
		int[] sums = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(seq);
		for(int i = 1; i < N + 1; i++) {				// prefix sum
			sums[i] += seq[i - 1] + sums[i - 1];
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			sb.append(sums[to] - sums[from - 1]).append(NEW_LINE);		// 부분 합
		}
		
		System.out.print(sb.toString());
	}
}
