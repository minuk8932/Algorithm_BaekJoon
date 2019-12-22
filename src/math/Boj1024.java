package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 1024번: 수열의 합
 *
 *	@see https://www.acmicpc.net/problem/1024/
 *
 */
public class Boj1024 {
	private static final String SPACE = " ";
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		System.out.println(sum(N, L));
	}
	
	private static String sum(int n, int l) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = l; i <= 100; i++) {
			int src = n / i;
			int mod = n % i;
			int val = 0;
			 
	        if(i % 2 == 0) {						// even
	            if(mod != i / 2) continue;
	            val = src - (i - 1) / 2;
	        }
	        else {									// odd
	            if(mod != 0) continue;
	            val = src - i / 2;
	        }
	        
	        if(val < 0) continue;
	        
	        for( int adder = 0; adder < i; adder++)  {
	        	sb.append(adder + val).append(SPACE);
	        }
	            
	        return sb.toString();
		}
		
		return sb.append(-1).toString();
	}
}
