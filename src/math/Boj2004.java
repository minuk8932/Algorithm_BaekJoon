package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2004번: 조합 0의 갯수
 *
 *	@see https://www.acmicpc.net/problem/2004/
 *
 */
public class Boj2004 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long n = Long.parseLong(st.nextToken());
		long k = Long.parseLong(st.nextToken());
		
		// 조합의 정의 활용
		System.out.println(Math.min(count(n, 2, 2) - count(k, 2, 2) - count(n - k, 2, 2), count(n, 5, 5) - count(k, 5, 5) - count(n - k, 5, 5)));
	}
	
	private static long count(long target, long value, long fix) {
		long count = 0;
		
		for(; target / value >= 1; value *= fix) {
			count += target / value;
		}
		
		return count;
	}
}
