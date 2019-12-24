package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 16680번: 안수빈수
 *
 *	@see https://www.acmicpc.net/problem/16680/
 *
 */
public class Boj16680 {
	private static final String NEW_LINE = "\n";
	private static final long INF = 1_000_000_000_000_000_000L;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			long N = Long.parseLong(br.readLine());
			sb.append(isNot(N)).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static long isNot(long n) {		
		while(n <= INF) {					// find all
			long loop = n;
			int sum = 0;
			
			while(loop > 0) {				// check sum
				long mod = loop % 10;
				sum += mod;
				
				loop /= 10;
			}
			
			if(sum % 2 == 1) return n;
			else n += n;
		}
		
		return -1;
	}
}
