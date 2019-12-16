package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 17614번: 369
 *
 *	@see https://www.acmicpc.net/problem/17614/
 *
 */
public class Boj17614 {
	private static boolean[] TSN;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		TSN = new boolean[N + 1];
		
		System.out.println(counting());
	}
	
	private static int counting() {
		int count = 0;
		
		for(int i = 1; i < TSN.length; i++) {
			int num = i;
				
			while(num > 0) {
				int mod = num % 10;
				
				if(mod != 0 && mod % 3 == 0) count++;
				num /= 10;
			}
		}
		
		return count;
	}
}
