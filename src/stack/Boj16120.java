package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * 
 * 	@author minchoba
 *	백준 16120번: PPAP
 *
 *	@see https://www.acmicpc.net/problem/16120/
 *
 */
public class Boj16120 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		
		System.out.println(line.length() >= 2 && !line.contains("A") ? "NP": isPPAP(line.toCharArray()));
	}
	
	private static String isPPAP(char[] arr) {
		ArrayDeque<Character> stack = new ArrayDeque<>();
		boolean flag = false;
		
		for(char c: arr) {
			if(c == 'A') {
				if(flag) {					// A 다음 A가 온 경우
					flag = false;
				}
				else {						// P 다음 A가 온 경우
					stack.push(c);
					if(stack.size() < 3) continue;
					
					char a = stack.pop();
					char pop = stack.pop();
					char peek = stack.peek();
					stack.push(pop);
					stack.push(a);
					
					if(pop == 'P' && peek == 'P') flag = true;		// A 앞에 PP 였으면 깃발을 세움
				}
			}
			else {
				if(flag) {				// 깃발이 선 상태에서 P가 다음으로 들어온 경우
					stack.pop();		// A pop
					stack.pop();		// P pop
					stack.pop();		// P pop
					stack.push(c);		// 현재 입력으로 들어온 P push
					flag = false;		// 깃발 내리기
				}
				else {
					stack.push(c);
				}
			}
		}
		
		while(!stack.isEmpty()) {				// PPAP를 모두 P로 바꿨는데, 스택에 A가 잔여한다면,
			if(stack.pop() == 'A') return "NP";
		}
		
		return "PPAP";
	}
}
