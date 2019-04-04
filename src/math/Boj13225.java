package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 13225번: Divisors
 *
 *	@see https://www.acmicpc.net/problem/13225/
 *
 */
public class Boj13225 {
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int C = Integer.parseInt(br.readLine());
		
		while(C-- > 0) {
			int n = Integer.parseInt(br.readLine());
			
			sb.append(n).append(SPACE).append(getDivisor(n)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int getDivisor(int n) {
		int count = 0;
		
		for(int i = 1; i <= n; i++) {
			if(n % i == 0) count++;
		}
		
		return count;
	}
}
