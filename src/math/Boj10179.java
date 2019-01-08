package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10179번: 할인
 *
 *	@see https://www.acmicpc.net/problem/10179/
 *
 */
public class Boj10179 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			double cost = Double.parseDouble(br.readLine());
			System.out.printf("$%.2f\n", discount(cost));		// 할인가 출력
		}
	}
	
	private static double discount(double num) {
		num = (num * 0.8);
		num *= 100;
		
		return Math.round(num) / 100.0;
	}
}
