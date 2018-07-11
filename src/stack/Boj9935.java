package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 
 * 	@author minchoba
 *	백준 9935번: 문자열 폭발
 *
 *	@see https://www.acmicpc.net/problem/9935/
 *
 */
public class Boj9935 {
	private static final String EMPTY = "FRULA";
	private static final int INF = 1_000_0001;
	private static char[] words = new char[INF];

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> stack = new Stack<>();
		words = br.readLine().toCharArray();
		char[] boom = br.readLine().toCharArray();
		
		int wLeng = words.length;	
		int bLeng = boom.length;
		
		char[] tmp = null;
		
		for(int i = 0; i < wLeng; i++) {
			tmp = new char[36];
			
			stack.push(words[i]);
			
			if(i >= bLeng - 1 && words[i] == boom[bLeng - 1]) {		// 폭발 문자열 길이 이상 스택에 담겨있고, 폭발문자열 마지막 문자가 현재 스택 상단의 문자와 같은 경우
				int cnt = 0, idx = 0;

				for(int j = bLeng - 1; j >= 0; j--) {	// 폭발 문자열을 뒤에서부터 가져와서
					if(stack.isEmpty())	break;
					
					tmp[idx] = stack.pop();		// 스택의 값을 하나씩 꺼내오며 비교
					if(tmp[idx] == boom[j]) {	// 같은경우 카운트 증가
						cnt++;
					}
					else {
						break;
					}
					
					idx++;
				}
				
				if(cnt == bLeng) continue;			// 카운트와 폭발 문자열 길이가 같으면 밖의 반복문을 다시 수행
				
				for(int j = idx; j >= 0; j--) {		// 다른 경우, 폭발 문자열이 아니므로 해당 문자열은 스택에 다시 있던대로 담아줌
					stack.push(tmp[j]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		String res = "";
		
		if(stack.isEmpty()) {		// 스택이 빈 경우
			sb.append(EMPTY);
			
			res = sb.toString();
		}
		else {
			int loop = stack.size();
			while(loop-- > 0) {
				sb.append(stack.pop());
			}
			
			res = sb.reverse().toString();		// 비지 않은 경우 버퍼에 담고 역순으로 문자 변수에 담음
		}
		
		System.out.println(res);		// 결과 값 한번에 출력
	}
}
