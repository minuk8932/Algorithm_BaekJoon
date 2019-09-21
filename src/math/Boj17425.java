package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 17425번: 약수의 합
 *
 *	@see https://www.acmicpc.net/problem/17452/
 *
 */
public class Boj17425 {
	private static final int INF = 1_000_000;
	private static long[] store = new long[INF + 1];
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		process();
		
		while(T-- > 0) {
			sb.append(store[Integer.parseInt(br.readLine())]).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static void process() {		
		for(int i = 1; i <= INF; i++) {
			for(int j = 1; j <= INF / i; j++) {			// j까지 약수
				store[i * j] += i;						// 약수의 합
			}
		}
		
		for(int i = 1; i <= INF; i++) {					// i까지 약수합의 전체합
			store[i] += store[i - 1];
		}
	}
}
