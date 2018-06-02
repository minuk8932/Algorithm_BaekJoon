package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1212번: 8진수 2진수
 *
 *	@see https://www.acmicpc.net/problem/1212/
 *
 */
public class Boj1212 {
	private static final String[] bin = {"000", "001", "010", "011", "100", "101", "110", "111"};
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String num = br.readLine();
		
		StringBuilder sb = new StringBuilder();
		
		boolean isPre = true;				// 맨 첫자리 구분
		
		for(char n : num.toCharArray()) {
			if(isPre) {
				sb.append(Integer.parseInt(bin[n - 48]));		// 가장 앞의 2진수는 정수형으로 버퍼에 담아줌
				isPre = false;
				
				continue;
			}
			
			sb.append(bin[n - 48]);			// 나머지는 문자열로 담아줌
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
