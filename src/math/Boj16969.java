package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 16969번: 차량 번호판 2
 *
 *	@see https://www.acmicpc.net/problem/16969/
 *
 */
public class Boj16969 {
	private static final int MOD = 1_000_000_009;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] number = br.readLine().toCharArray();
		
		System.out.println(getCount(number));
	}
	
	private static long getCount(char[] arr) {
		long result = 1;
		char prev = ' ';
		
		int num = 10, alpha = 26;
		
		for(char c: arr) {
			if(c == prev) {
				if(c == 'c') {				// 이전 것과 같으면 총 갯수 -1
					alpha = 25;
					result = ((result % MOD) * alpha) % MOD;
				}
				else {
					num = 9;
					result = ((result % MOD) * num) % MOD;
				}
			}
			else {							// 이전 것과 다른 타입이면 갯수 초기화
				num = 10;
				alpha = 26;
				
				if(c == 'c') result = ((result % MOD) * alpha) % MOD;
				else result = ((result % MOD) * num) % MOD;
			}
			
			prev = c;
		}
		
		return result % MOD;
	}
}
