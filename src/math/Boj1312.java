package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1312번: 소수
 *
 *	@see https://www.acmicpc.net/problem/1312/
 *
 */
public class Boj1312 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		System.out.println(getNumber(A, B, N));
	}
	
	private static int getNumber(int a, int b, int n) {
		int value = 0;
		a %= b;				// 소수부 부터 바로 뽑을 수 있도록
		
		while(n-- > 0) {
			a *= 10;
			value = a / b;	// 소수부 값
			a -= b * value;
		}
		
		return n > 0 ? 0: value;
	}
}
