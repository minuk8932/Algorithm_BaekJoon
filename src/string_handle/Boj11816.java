package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 11816번: 8진수, 10진수, 16진수
 *
 *	@see https://www.acmicpc.net/problem/11816/
 *
 */
public class Boj11816 {
	private static final char ZERO = '0';
	private static final char X = 'x';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] num = br.readLine().toCharArray();
		
		StringBuilder sb = new StringBuilder();
		
		if(num[0] == ZERO) {					// 맨앞자리가 0인 경우
			if(num[1] == X) {						// 그다음이 x이면 16진수
				System.out.println(hex(num, sb));
			}
			else {									// 아니면 8진수
				System.out.println(octal(num, sb));
			}
		}
		else {									// 그외 10진수
			System.out.println(dec(num, sb));
		}
	}
	
	private static String octal(char[] n, StringBuilder sb) {		// 8진수 생성 메소드
		int res = 0;
		
		for(int i = 1; i < n.length; i++) {
			res += Character.getNumericValue(n[i]) * Math.pow(8, n.length - (i + 1));
		}
		sb.append(res);
		
		return sb.toString();
	}
	
	private static String hex(char[] n, StringBuilder sb) {		// 16진수 생성 메소드
		int res = 0;
		
		for(int i = 2; i < n.length; i++) {
			int tmp = 0;
			if(n[i] >= 'a' && n[i] <= 'f') {			// 들어오는 값이 a~f 사이라면, 숫자 10~15로 변형
				tmp = n[i] - 87;
			}
			else {
				tmp = Character.getNumericValue(n[i]);
			}
				res += tmp * Math.pow(16, n.length - (i + 1));
		}
		sb.append(res);
		
		return sb.toString();
	}
	
	private static String dec(char[] n, StringBuilder sb) {		// 10진수 생성 메소드
		for(int i = 0; i < n.length; i++) {
			sb.append(n[i]);
		}
		
		return sb.toString();
	}
}
