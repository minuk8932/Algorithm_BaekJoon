package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * 
 * 	@author minchoba
 *	백준 15815번: 천재 수학자 성필
 *
 *	@see https://www.acmicpc.net/problem/15815/
 *
 */
public class Boj15815 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		
		System.out.println(getValue(line));
	}
	
	private static int getValue(String str) {
		char[] arr = str.toCharArray();
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		
		for(char c: arr) {
			if(c >= '0' && c <= '9') {		// 숫자인 경우 바로 스택에 저장
				stack.push(c - '0');
			}
			else {
				if(!stack.isEmpty()) {		// 숫자가 아니면 뽑아와서
					int b = stack.pop();
					int a = stack.pop();
					
					int value = 0;
					
					switch (c) {			// 연산 후 다시 스택으로
					case '+':
						value = a + b;
						break;
						
					case '-':
						value = a - b;
						break;
						
					case '*':
						value = a * b;
						break;
						
					case '/':
						value = a / b;
						break;
					}
					
					stack.push(value);
				}
			}
		}
		
		return stack.pop();
	}
}
