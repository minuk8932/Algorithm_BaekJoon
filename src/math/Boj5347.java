package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5347번: LCM
 *
 *	@see https://www.acmicpc.net/problem/5347/
 *
 */
public class Boj5347 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(n-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			
			long res = a * b / gcd(a, b);		// 최대 공약수를 이용한 최소 공배수 도출
			sb.append(res).append(NEW_LINE);	// 최소 공배수를 각각 버퍼에 담아줌
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
	
	/**
	 * 유클리드 호제법을 이용한 최대 공약수 반환
	 * @param 비교할 숫자
	 * @return 최대 공약수
	 */
	private static long gcd(long x, long y) {
		if(y == 0) {
			return x;
		}
		
		return gcd(y, x % y);
	}
}
