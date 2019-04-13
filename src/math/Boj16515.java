package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 16515번: Euler's Number
 *
 *	@see https://www.acmicpc.net/problem/16515/
 *
 */
public class Boj16515 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		System.out.println(getEulerNumber(n));
	}
	
	private static double getEulerNumber(int n) {
		double res = 1, cache = 1;
		
		for(int i = 1; i < n + 1; i++) {
			cache *= i;						// 곱 누적
			res += (1 / cache);
		}
		
		return res;
	}
}
