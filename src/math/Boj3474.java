package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 3474번: 교수가 된 현우
 *
 *	@see htpts://www.acmicpc.net/problem/3474/
 *
 */
public class Boj3474 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			long N = Long.parseLong(br.readLine());
			sb.append(Math.min(count(N, 2, 2), count(N, 5, 5))).append(NEW_LINE);		// 2와 5의 공통 갯수
		}
		
		System.out.println(sb);
	}
	
	private static long count(long target, long value, long fix) {
		long count = 0;
		
		for(; target / value >= 1; value *= fix) {
			count += target / value;
		}
		
		return count;
	}
}
