package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 
 * 	@author minchoba
 *	백준 1662번: 압축
 *
 *	@see https://www.acmicpc.net/problem/1662/
 *
 */
public class Boj1662 {
	private static final char CLOSE = ')';
	private static final char OPEN = '(';
	
	private static int res = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String compressed = br.readLine();
		
		Stack<Elements> lifo = new Stack<>();
		
		for(char c: compressed.toCharArray()) {
			if(c != CLOSE) {
				if(c == OPEN) lifo.push(new Elements(-1, false));		// 열린 괄호가 나올 경우 -1
				else lifo.push(new Elements((c - '0'), false));		// 숫자가 나올 경우 해당 숫자와 아직 더해지지 않음(false)를 스택에 push
			}
			else {
				int leng = 0;
				
				while(lifo.peek().num != -1) {		// 열린 괄호가 나오기 전 까지
					Elements peek = lifo.pop();
					
					if(peek.isSum) leng += peek.num;		// 만약 이미 연산이 되었던 수(길이)라면, 해당 수 + 길이
					else leng++;						// 입력으로 주어졌던 수라면 길이 + 1
				}
				
				lifo.pop();				// 열린 괄호 제거
				
				leng *= lifo.pop().num;				// 괄호 내 값을 합한 길이에 괄호 밖의 수로 배수를 해줌
				lifo.push(new Elements(leng, true));	// 결과를 다시 스택에 담아줌
			}
		}

		while(!lifo.isEmpty()) {			// 길이 연산이 끝난 값 들을 꺼내와서 총 길이를 구함
			Elements tmp = lifo.pop();
			res += tmp.isSum ? tmp.num : 1;		// 괄호 밖에서 아직 연산이 안된 입력 수가 있는지 체크 후 각각 개별 연산
		}
		
		System.out.println(res);			// 결과 값 출력
	}
	
	/**
	 * 내부 원소 구분 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Elements{
		int num;
		boolean isSum;
		
		public Elements(int num, boolean isSum) {
			this.num = num;
			this.isSum = isSum;
		}
	}
}
