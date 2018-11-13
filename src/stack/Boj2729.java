package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2729번: 이진수 덧셈
 *
 *	@see https://www.acmicpc.net/problem/2729/
 *
 */
public class Boj2729 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String b1 = st.nextToken();
			String b2 = st.nextToken();
			
			int leng1 = b1.length();
			int leng2 = b2.length();
			
			int leng = Math.max(leng1, leng2);
			char[] bi1 = new char[leng];
			char[] bi2 = new char[leng];
			
			if(leng1 > leng2) {							// 길이가 안맞는 이진수의 길이를 맞춰줌
				for(int i = 0; i < leng; i++) {
					bi1[i] = b1.charAt(i);
					
					if(i < leng - leng2) bi2[i] = '0';
					else bi2[i] = b2.charAt(i - (leng - leng2));
				}
			}
			else {
				for(int i = 0; i < leng; i++) {
					bi2[i] = b2.charAt(i);
					
					if(i < leng - leng1) bi1[i] = '0';
					else bi1[i] = b1.charAt(i - (leng - leng1));
				}
			}
			
			Stack<Character> lifo = new Stack<>();
			int carry = 0, counts = 0;
			
			for(int i = leng - 1; i >= 0; i--) {		// 조건에 따라 스택에 들어갈 값을 결정
				if(bi1[i] == '0' && bi2[i] == '0') {
					counts++;
					
					if(carry == 0) {
						lifo.push('0');
					}
					else {
						lifo.push('1');
						carry = 0;
					}
				}
				else if((bi1[i] == '0' && bi2[i] == '1') || (bi1[i] == '1' && bi2[i] == '0')) {
					if(carry == 0) {
						lifo.push('1');
					}
					else {
						lifo.push('0');
						carry = 1;
					}
				}
				else {
					if(carry == 0) {
						lifo.push('0');
						carry = 1;
					}
					else {
						lifo.push('1');
						carry = 1;
					}
				}
			}
			
			if(carry == 1) lifo.push('1');			// 마지막 연산 중 캐리가 발생 할 경우
			
			if(counts == leng) {				// 모든 값이 0인 경우
				sb.append(0);
			}
			else {
				boolean isOne = false;
				
				while(!lifo.isEmpty()) {
					if(lifo.peek() == '1') isOne = true; 
					
					if(isOne) sb.append(lifo.pop());		// 1이 나오기전까진 앞자린 필요없으므로 다 버림
					else lifo.pop();
				}
			}
			
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb);			// 결과 값 한번에 출력 
	}
}
