package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13241번: 최소 공배수
 *
 *	@see https://www.acmicpc.net/problem/13241/
 *
 */
public class Boj13241 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		long max = Math.max(a, b);
		long min = Math.min(a, b);
		
		long mod = max % min;
		
		long gcd = 0;
		
		if(mod == 0) {		// 바로 최대 공약수가 구해지는 경우
			gcd = min;
		}
		else {				// 그 외, 유클리드 호제법과 gcd 메소드를 통한 최대 공약수를 구함
			max -= min;
			long val = min % max == 0 ? min / max - 1 : min / max;
			min -= (max * val);
			
			gcd = getGcd(min, max);
		}
		
		System.out.println(a * b / gcd);		// 최소 공배수 공식을 통해 결과 값 출력
	}
	
	/**
	 * 최대 공약수 메소드
	 * 
	 */
	private static long getGcd(long num1, long num2) {
		if(num2 == 0) return num1;
		
		return getGcd(num2, num1 % num2);
	}
}
