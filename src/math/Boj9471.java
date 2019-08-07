package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 9471번: 피사노 주기
 *
 *	@see https://www.acmicpc.net/problem/9471/
 *
 */
public class Boj9471 {	
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int P = Integer.parseInt(br.readLine());
		
		while(P-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			sb.append(N).append(SPACE).append(getPeriod(M)).append(NEW_LINE);
		}
		
		System.out.print(sb.toString());
	}
	private static int getPeriod(int m) {
		int[] fibo = {1, 2 % m};
		int count = 1;
		
		if(fibo[0] == 1 && fibo[1] == 1) return count;
		
		while(fibo[0] != 1 || fibo[1] != 1) {			// 주기가 반복 될 때 까지
			int tmp = fibo[0];
			fibo[0] = fibo[1];
			fibo[1] = (tmp + fibo[0]) % m;
			
			count++;
		}
		
		return count;
	}
}
