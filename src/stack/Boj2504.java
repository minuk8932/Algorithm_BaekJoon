package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 
 * 	@author minchoba
 *	백준 2504번: 괄호의 값
 *
 *	@see https://www.acmicpc.net/problem/2504/
 *
 */
public class Boj2504 {
	private static int res = 0;
	private static int tRes = 1;
	private static boolean terminate = false;
	
	private static Stack<Character> lifo = new Stack<>();

	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final char TWO_P = '(';
	private static final char THREE_P = '[';
	private static final char SHUT_TWO_P = ')';
	private static final char SHUT_THREE_P = ']';
	
	private static final int NOT_CORRECT = 0;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] line = br.readLine().toCharArray();
		
		System.out.println(parenthesisValues(line));	// 괄호값 메소드를 통한 결과값 출력
	}
	
	/**
	 * 괄호 값 메서드
	 * 
	 */
	private static int parenthesisValues(char[] pa) {		
		for (int i = 0; i < pa.length; i++) {			
			switch (pa[i]) {
			case TWO_P:				// '('의 경우
				tRes *= TWO;		// 임시 결과값 * 2
				lifo.push(TWO_P);	// 스택에 '(' push
				break;
				
			case THREE_P:			// '['의 경우
				tRes *= THREE;		// 임시 결과값 * 3
				lifo.push(THREE_P);	// 스택에 '[' push
				break;
				
			case SHUT_TWO_P:			// ')'의 경우
				if(lifo.isEmpty()) {	// 스택이 비어있으면, 잘못된 괄호이므로 종료
					terminate = true;
					break;
				}
				
				if(pa[i - 1] == TWO_P) res += tRes;		// 직전 값만 ')'인 경우 결과값 + (임시 결과)
				if (lifo.peek() == TWO_P) lifo.pop();	// 스택 맨 위의 값이 ')'인 경우 pop
				
				tRes /= TWO;
				break;

			case SHUT_THREE_P:			// ']'의 경우
				if(lifo.isEmpty()) {	// 스택이 비어있으면, 잘못된 괄호이므로 종료
					terminate = true;
					break;
				}
				
				if(pa[i - 1] == THREE_P) res += tRes;	// 직전 값만 ']'인 경우 결과값 + (임시 결과)
				if (lifo.peek() == THREE_P) lifo.pop();	// 스택 맨 위의 값이 ']'인 경우 pop
				
				tRes /= THREE;
				break;
			}
			
			if(terminate) break;
		}

		return terminate || !lifo.isEmpty() ? NOT_CORRECT : res;	// 비정상 종료 했거나, 스택에 값이 남아있는 경우 0, 그외 res 반환
	}
}
