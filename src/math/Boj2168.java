package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 2168번: 타일 위의 대각선
 *
 *	@see https://www.acmicpc.net/problem/2168/
 *
 */
public class Boj2168 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		System.out.println(x + y - gcd(x, y));		// 두 선분의 합 - 최대공약수
	}
	
	private static int gcd(int a, int b) {
		if(b == 0) return a;
		return gcd(b, a % b);
	}
}
