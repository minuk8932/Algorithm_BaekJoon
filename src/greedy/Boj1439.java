package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * 
 * 	@author minchoba
 *	백준 1439번: 뒤집기
 *
 *	@see https://www.acmicpc.net/problem/1439/
 *
 */
public class Boj1439 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] seq = br.readLine().toCharArray();
		
		System.out.println(getCount(seq));
	}
	
	private static int getCount(char[] arr) {
		ArrayDeque<Character> stack = new ArrayDeque<>();
		char tmp = ' ';
		
		for(char c: arr) {
			if(c == tmp) continue;		// 연속으로 들어오는 것 하나만 받고 다버림
			tmp = c;
			stack.push(c);
		}
		
		int count = 0, size = stack.size();
		char top = stack.peek();
		char bottom = ' ';
		
		while(!stack.isEmpty()) {
			bottom = stack.pop();
			if(top == bottom) count++;
		}
		
		if(size - count > count) count -= size;		// 다른 문자가 더 적으면 그 숫자로 바꿔줌
		return top == bottom ? count - 1: count;	// 맨앞과 맨뒤가 같은 문자면 -1
	}
}
