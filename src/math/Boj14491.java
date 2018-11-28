package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 14491번: 9진수
 *
 *	@see https://www.acmicpc.net/problem/14491/
 *
 */
public class Boj14491 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		System.out.println(converter(T));		// 결과 출력
	}
	
	private static StringBuilder converter(int num) {
		StringBuilder sb = new StringBuilder();
		
		while(num > 0) {
			sb.append(num % 9);
			num /= 9;
		}
		
		return sb.reverse();
	}
}
