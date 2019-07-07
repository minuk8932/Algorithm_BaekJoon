package math.modular_inverse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 4233번: 가짜 소수
 *
 *	@see https://www.acmicpc.net/problem/4233/
 *
 */
public class Boj4233 {
	private static final String NEW_LINE = "\n";
	private static final String Y = "yes";
	private static final String N = "no";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long p = Long.parseLong(st.nextToken());
			long a = Long.parseLong(st.nextToken());
			
			if(a == 0 && p == 0) break;
			// 소수가 아닐 때, 페르마 소 정리가 적용이 되는가?
			sb.append(notPrime(p) && modularInverse(p, a, p) ? Y : N).append(NEW_LINE);
		}
		
		System.out.print(sb);
	}
	
	private static boolean modularInverse(long p, long a, long mod) {
		long result = 1, tmp = a;
		
		while(p != 0) {						// 잉여 역수 연산
			if((p & 1) == 1) result = (result * tmp) % mod;
			tmp = (tmp * tmp) % mod;
			
			p >>= 1;
		}

		return result == a ? true : false;
	}
	
	private static boolean notPrime(long num) {
		for(int i = 2; i * i <= num; i++) {
			if(num % i == 0) return true;
		}
		
		return num != 1 ? false : true;
	}
}
