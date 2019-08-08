package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * 
 * 	@author exponential-e
 *	백준 1247번: 부호
 *
 *	@see https://www.acmicpc.net/problem/1247/
 *
 */
public class Boj1247 {
	private static final String ZERO = "0";
	private static final String PLUS = "+";
	private static final String MINUS = "-";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int loop = 3;
		
		while(loop-- > 0) {
			int N = Integer.parseInt(br.readLine());
			
			BigDecimal sum = new BigDecimal(ZERO);
			for(int i = 0; i < N; i++) {
				BigDecimal input = new BigDecimal(br.readLine());
				sum = sum.add(input);
			}
			
			sb.append(sum.equals(new BigDecimal(ZERO)) ? ZERO: sum.abs().equals(sum) ? PLUS: MINUS).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
}
