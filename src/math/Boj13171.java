package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 13171번: A
 *
 *	@see https://www.acmicpc.net/problem/13171/
 *
 */
public class Boj13171 {
	private static long MOD = 1_000_000_007;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long A = Long.parseLong(br.readLine());
		long X = Long.parseLong(br.readLine());
		
		System.out.println(getResult(A, X));
	}
	
	private static long getResult(long a, long x) {
		long res = 1;
		
		while(x > 0) {					// 문제 내용 참고.
			if(x % 2 == 1) {
				res = getMod(a, res);
			}
			
			a = getMod(a, a);
			x /= 2;
		}
		
		return res;
	}
	
	private static long getMod(long num1, long num2) {
		return ((num1 % MOD) * (num2 % MOD)) % MOD;
	}
}
