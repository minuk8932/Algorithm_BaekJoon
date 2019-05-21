package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 17214번: 다항 함수의 적분
 *
 *	@see https://www.acmicpc.net/problem/17214/
 *
 */
public class Boj17214 {
	private static final String X = "x";
	private static final String PLUS = "+";
	private static final String MINUS = "-";
	private static final String INTEGRAL_CONSTANT = "W";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String polynomial = br.readLine();
		
		System.out.println(getIntegral(polynomial));
	}
	
	private static StringBuilder getIntegral(String line) {
		StringBuilder sb = new StringBuilder();
		if(line.equals("0")) return sb.append(INTEGRAL_CONSTANT);		// 0인 경우
		
		if(!line.contains(X)) {			// 상수 식
			return sb.append(line.equals("1") || line.equals("-1") ? (line.equals("1") ? X: MINUS + X): line + X).append(PLUS).append(INTEGRAL_CONSTANT);
		}
		else {
			boolean prev = true, post = true;					// 참이면 계수 +, 거짓이면 계수 -
			if(line.charAt(0) == MINUS.charAt(0)) prev = false;
			
			String next = line.substring(1);
			if(next.contains(MINUS)) post = false;
			
			int idx = line.indexOf(X);
			int head = Integer.parseInt(line.substring(0, idx)) / 2;
			if(line.length() - 1 == idx) {							// 1차식만 있는 경우				
				return sb.append(head == 1 || head == -1 ? (prev ? X + X: MINUS + X + X) : head + X + X).append(PLUS).append(INTEGRAL_CONSTANT);
			}
			else {													// 1차식 + 상수
				int tail = Integer.parseInt(line.substring(idx + 1));
				return sb.append(head == 1 || head == -1 ? (prev ? X + X: MINUS + X + X) : head + X + X).append(post ? PLUS: "")
						.append(tail == 1 || tail == -1 ? (post ? X: MINUS + X) : tail + X).append(PLUS).append(INTEGRAL_CONSTANT);
			}
		}
	}
}
