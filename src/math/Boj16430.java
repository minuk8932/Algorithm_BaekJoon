package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16430번: 제리와 톰
 *
 *	@see https://www.acmicpc.net/problem/16430/
 *
 */
public class Boj16430 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		A = B - A;
		int gcd = getGcd(B ,A);
		if(gcd != 1) {
			A /= gcd;
		}
		
		System.out.println(A + " " + B);		// 결과 출력
	}
	
	private static int getGcd(int num1, int num2) {
		if(num2 == 0) return num1;
		return getGcd(num2, num1 % num2);
	}
}
