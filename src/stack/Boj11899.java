package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 
 * 	@author minchoba
 *	백준 11899번: 괄호 끼워넣기
 *
 *	@see https://www.acmicpc.net/problem/11899/
 *
 */
public class Boj11899 {
	private static final char OPEN = '(';
	private static final char CLOSE = ')';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> paren = new Stack<>();
		
		for(char tmp : br.readLine().toCharArray()) {				// 입력을 하나씩 받아온 후	
			if(tmp == CLOSE) {									// 닫는 괄호일 때
				if(!paren.isEmpty() && paren.peek() == OPEN) {	// 스택의 가장 윗부분에 여는 괄호가 있다면
					paren.pop();							// 여는 괄호를 빼주고 다음 반복문으로
					
					continue;
				}				
			}
			
			paren.push(tmp);				// pop 연산이 실행되지 않은 경우 해당 괄호를 스택에 넣어줌
		}
		
		System.out.println(paren.size());	// 스택에 남은 괄호 갯수를 출력
	}
}
