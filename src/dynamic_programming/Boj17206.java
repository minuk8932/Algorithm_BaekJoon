package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17206번: 준석이의 수학 숙제
 *
 *	@see https://www.acmicpc.net/problem/17206/
 *
 */
public class Boj17206 {
	private static final String NEW_LINE = "\n";
	private static int[] sums = new int[80_001];

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		init();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(T-- > 0) {
			int range = Integer.parseInt(st.nextToken());
			sb.append(sums[range]).append(NEW_LINE);
		}
		
		System.out.print(sb);
	}
	
	private static void init() {
		boolean[] divisor = new boolean[80_001];
		
		for(int i = 0; i < divisor.length; i++) {
			if(i % 3 == 0 || i % 7 == 0) divisor[i] = true;		// 3과 7의 약수 체크
		}
		
		for(int i = 1; i < sums.length; i++) {
			if(divisor[i]) sums[i] = sums[i - 1] + i;			// 해당 수가 약수면 더해주
			else sums[i] = sums[i - 1];							// 아니면 이전 수만 유지
		}
	}
}
