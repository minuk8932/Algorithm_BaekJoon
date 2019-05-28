package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10829번: 이진수 변환
 *
 *	@see https://www.acmicpc.net/problem/10829/
 *
 */
public class Boj10829 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		
		System.out.println(getBinary(N));
	}
	
	private static String getBinary(long n) {
		StringBuilder sb = new StringBuilder();
		
		while(n > 0) {
			sb.append(n % 2);		// 이진수를 역순으로 저장
			n /= 2;
		}
		
		return sb.reverse().toString();
	}
}
