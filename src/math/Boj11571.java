package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 11571번: 분수를 소수로
 *
 *	@see https://www.acmicpc.net/problem/11571/
 *
 */
public class Boj11571 {
	private static final String DOT = ".";
	private static final String OPEN = "(";
	private static final String CLOSE = ")\n";
	
	private static StringBuilder sb = new StringBuilder();
	private static int prev;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		int[] list = new int[20_000];
		
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int son = Integer.parseInt(st.nextToken());
			int mom = Integer.parseInt(st.nextToken());
			
			Arrays.fill(list, -1);

			sb.append(son / mom).append(DOT);			
			prev = son % mom * 10;
			int idx = 0;

			int left = 0, right = 0;
			while (true) {
				son %= mom;
				son *= 10;
				
				if (list[son] != -1) break;			// 순환부 찾기
				list[son] = idx++;
			}
			
			left = list[son];						// 순환부 범위
			right = idx;
			
			partition(0, left, mom);				// 비 순환부 구하기
			
			sb.append(OPEN);
			partition(left, right, mom);			// 순환부 구하기
			sb.append(CLOSE);
		}
		
		System.out.print(sb.toString());
	}
	
	private static void partition(int from, int to, int under) {
		for(int i = from; i < to; i++) {
			sb.append(prev / under);
			
			prev %= under;
			prev *= 10;
		}
	}
}
