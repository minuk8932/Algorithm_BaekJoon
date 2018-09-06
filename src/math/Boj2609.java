package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2609번: 최대 공약수와 최대 공배수
 *
 *	@see https://www.acmicpc.net/problem/2609/
 *
 */
public class Boj2609 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int gcd = getGcd(a, b);		// 최대 공약수 구함
		int lcm = a * b / gcd;		// 최대 공약수를 통한 최대 공배수 계산

		System.out.println(gcd + NEW_LINE + lcm);		// 결과 값 한번에 출력
	}
	
	/**
	 * 최대 공약수 메소드
	 * 
	 */
	private static int getGcd(int x, int y) {
		if(y == 0) {
			return x;
		}
		
		return getGcd(y, x % y);
	}
}
