package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1850번: 최대공약수
 *
 *	@see https://www.acmicpc.net/problem/1850/
 *
 */
public class Boj1850 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long gcd = getGcd(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
		System.out.println(process(gcd));		// 결과 출력
	}
	
	private static long getGcd(long A, long B) {
		if(B == 0) return A;
		return getGcd(B, A % B);
	}
	
	private static StringBuilder process(long loop) {		// 최대 공약수의 크기 만큼 1을 출력
		StringBuilder sb = new StringBuilder();
		
		while(loop-- > 0) {
			sb.append(1);
		}
		
		return sb;
	}
}
