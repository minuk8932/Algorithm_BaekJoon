package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1735번: 분수 합
 *
 *	@see https://www.acmicpc.net/problem/1735/
 *
 */
public class Boj1735 {
	private static final String SPACE = " ";
	
	private static int[] nume = new int[2];
	private static int[] deno = new int[2];
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		nume[0] = Integer.parseInt(st.nextToken());		// 분자1
		deno[0] = Integer.parseInt(st.nextToken());		// 분모1
		
		st = new StringTokenizer(br.readLine());
		nume[1] = Integer.parseInt(st.nextToken());		// 분자2
		deno[1] = Integer.parseInt(st.nextToken());		// 분모2
		
		int denominator = lcm(deno[0], deno[1]);		// 통분된 분모
		int numerator = (denominator / deno[0] * nume[0]) + (denominator / deno[1] * nume[1]);	// 분모를 통한 분자 계산
		
		int quotient = gcd(numerator, denominator);		// 기약분수 처리
		
		StringBuilder sb = new StringBuilder();
		sb.append(numerator / quotient).append(SPACE).append(denominator / quotient);	// 결과를 버퍼에 담은 후
		System.out.println(sb.toString());			// 결과값 한번에 출력
	}
	
	/**
	 * @param a 분모1
	 * @param b 분모2
	 * @return 최소 공배수
	 */
	private static int lcm(int a, int b) {
		if(b == 0) {
			return deno[0] * deno[1] / a;
		}
		
		return lcm(b, a % b);
	}
	
	/**
	 * 
	 * @param a 통합 분모
	 * @param b 통합 분자
	 * @return 최대 공약수
	 */
	private static int gcd(int a, int b) {
		if(b == 0) {
			return a;
		}
		
		return gcd(b, a % b);
	}
}
