package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 4928번: 큰 수
 *
 *	@see https://www.acmicpc.net/problem/4928/
 *
 */
public class Boj4928 {
	private static final int MOD = 20_000_303;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] seq = br.readLine().toCharArray();
		
		System.out.println(getModular(seq));
	}
	
	private static long getModular(char[] arr) {
		long value = 0;		
		
		for(int i = 0; i < arr.length; i++) {			// 가장 앞자리에서부터 나누어 나간다.
			value += arr[i] - '0';
			
			if(value >= MOD) value %= MOD;			
			value *= 10;
		}
		
		return value / 10;
	}
}
