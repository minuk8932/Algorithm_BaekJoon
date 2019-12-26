package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 11564번: 점프왕 최준민
 *
 *	@see https://www.acmicpc.net/problem/11564/
 *
 */
public class Boj11564 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long k = Long.parseLong(st.nextToken());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		System.out.println(chocolates(k, a, b));
	}
	
	private static long chocolates(long k, long s, long e) {
		long res = 0;
		
		if (s <= 0 && e >= 0) {				// include zero
			res++;
			res += Math.abs(e / k);
			res += Math.abs(s / k);
		}
		else {
			if (s < 0 && e < 0) res = make(k, e, s);
			else res = make(k, s, e);
		}
		
		return res;
	}
	
	private static long make(long k, long s, long e) {
		long val = 0;
		
		val += Math.abs(e / k);
		val -= Math.abs(s / k);
		if (s % k == 0) val++;
		
		return val;
	}
}
