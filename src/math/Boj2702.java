package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2702번: Sixth grade math
 *
 *	@see https://www.acmicpc.net/problem/2702/
 *
 */
public class Boj2702 {
	private static final char NEW_LINE = '\n';
	private static final char SPACE = ' ';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int gcd = getGcd(a, b);
			int lcm = (a * b) / gcd;
			
			sb.append(lcm).append(SPACE).append(gcd).append(NEW_LINE);
		}
		
		System.out.println(sb);		// 결과 출력
	}
	
	private static int getGcd(int num1, int num2) {		// 최대 공약수 메소드
		if(num2 == 0) return num1;
		return getGcd(num2, num1 % num2);
	}
}
