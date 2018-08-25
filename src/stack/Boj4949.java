package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 
 * 	@author minchoba
 *	백준 4949번: 균형잡힌 세상
 */
public class Boj4949 {
	private static final String NEW_LINE = "\n";
	private static final String TERMINATE = ".";
	
	private static Stack<Character> lifo = new Stack<>();
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = br.readLine();
			if(TERMINATE.equals(line)) break;		// 종료 조건이 들어오면 반복문 종료
			
			sb.append(isBalanced(line) ? "yes" : "no").append(NEW_LINE);		// 밸런스 메소드가 true 면 yes, false면 no를 버퍼에 담음
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
	
	/**
	 * 균형 잡힌 문자열을 가려내는 메소드
	 * 
	 */
	private static boolean isBalanced(String str) {
		lifo = new Stack<>();
		
		for(char w : str.toCharArray()) {			
			switch (w) {			// 입력으로 '(', '['가 들어오면 스택에 담아줌
			case '(':
			case '[':
				lifo.push(w);
				break;
				
			case ')':
				if(lifo.isEmpty()) return false;		// ')', ']'가 들어왔을땐
				if(lifo.peek() != '(') return false;	// 스택이 비어있거나 해당 괄호와 짝이 아니라면 거짓으로 바로 반환하고
				
				lifo.pop();								// 짝에 맞는 괄호가 스택에 있다면 pop
				break;
				
			case ']':
				if(lifo.isEmpty()) return false;
				if(lifo.peek() != '[') return false;
				
				lifo.pop();
				break;
			}
		}
		
		return lifo.isEmpty() ? true : false;			// 모든 반복문이 종료되었으나 스택이 비어있지 않으면 false, 아니면 true 반환
	}
}
