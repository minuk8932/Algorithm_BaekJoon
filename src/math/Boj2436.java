package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2436번: 공약수
 *
 *	@see https://www.acmicpc.net/problem/2436/
 *
 */
public class Boj2436 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int gcd = Integer.parseInt(st.nextToken());
		int lcm = Integer.parseInt(st.nextToken());
		
		int target = lcm / gcd;				// LCM = A * B * GCD
		int left = 0, right = 0;
			
		for(int i = 1; i * i < target + 1; i++) {		// 어떤 값 target의 루트는 항상 약수의 가장 중앙
			if(target % i == 0) {
				if (getGcd(i, target / i) == 1) {		// GCD 구하기
					left = i;
					right = target / i;
				}
			}
		}
		
		int max = Math.max(left, right) * gcd;
		int min = Math.min(left, right) * gcd;
		
		System.out.println(min + " " + max);
	}
	
	private static int getGcd(int a, int b) {
		return b > 0 ? getGcd(b, a % b) : a;
	}
}
