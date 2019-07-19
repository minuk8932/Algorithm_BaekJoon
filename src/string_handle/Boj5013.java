package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 5013번: Death Knight Hero
 *
 *	@see https://www.acmicpc.net/problem/5013/
 *
 */
public class Boj5013 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int wins = 0;
		
		while(n-- > 0) {
			wins += battles(br.readLine().toCharArray());
		}
		
		System.out.println(wins);
	}
	
	private static int battles(char[] seq) {
		boolean flag = false;
		
		for(char c: seq) {
			if(c == 'C') {
				flag = true;
				continue;
			}
			
			if(flag) {
				if(c == 'D') return 0;		// CD가 연속해서 나오면 0
				else flag = false;
			}
		}
		
		return 1;
	}
}
