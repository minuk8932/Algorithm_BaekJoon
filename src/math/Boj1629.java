package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	
 *	백준 1629번 : 곱셈
 *
 *	@see https://www.acmicpc.net/problem/1629
 *
 */

public class Boj1629 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		long A = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
		long C = Integer.parseInt(st.nextToken());
		
		System.out.println(res(A % C, B, C) % C);		// 결과값 출력
	}
	
	/**
	 * 
	 * @param x
	 * @param n
	 * @param mod : 나머지 연산
	 * @return x^n
	 * 
	 * 일반적인 방법으로 하면, 범위초과 시간초과가 발생하므로, 지속적인 나머지 연산과 함께
	 * repeated squaring algorithm을 이용한다.
	 * 
	 */
	private static long res(long x, long n, long mod) { 		//x^n을 반환

		if (n == 0)
			return 1;

		if (n % 2 == 1){												//n이 홀수일 때
			return ((x % mod) * (res(x, n - 1, mod) % mod)) % mod; 				
		}
		else { 															//n이 짝수일 때
			long half = res(x, n / 2, mod) % mod;
			
			return (half * half) % mod; 						
		}
	}
}
