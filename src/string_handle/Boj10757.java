package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10757: 큰 수 A + B
 *
 *	@see https://www.acmicpc.net/problem/10757/
 *
 */
public class Boj10757 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String n1 = st.nextToken();
		String n2 = st.nextToken();
		
		int leng1 = n1.length();
		int leng2 = n2.length();
		int leng = 0;
		
		char[] num1 = null;
		char[] num2 = null;
		
		if(leng1 > leng2) {				// 길이가 짧은 것이 있다면 앞에 짧은 만큼 0을 붙여줌
			num1 = n1.toCharArray();
			num2 = new char[leng1];
			
			leng = leng1;
			
			int idx = 0;
			
			for(int i = 0; i < leng1; i++) {
				if(i < leng1 - leng2) {
					num2[i] = '0';
				}
				else {
					num2[i] = n2.charAt(idx++);
				}
			}
		}
		else {
			num2 = n2.toCharArray();
			num1 = new char[leng2];
			
			leng = leng2;
			
			int idx = 0;
			
			for(int i = 0; i < leng2; i++) {
				if(i < leng2 - leng1) {
					num1[i] = '0';
				}
				else {
					num1[i] = n1.charAt(idx++);
				}
			}
		}
		
		Stack<Integer> res = new Stack<>();
		int carry = 0;
		
		for(int i = leng - 1; i >= 0; i--) {
			int tmp = (num1[i] - '0') + (num2[i] - '0') + carry;
			
			if(tmp > 9) {		// 더한 수를 스택에 넣어주면서, 9보다 커지는 경우엔 carry를 1로 바꾸어 계산
				tmp -= 10;
				res.push(tmp);
				
				carry = 1;
			}
			else {
				res.push(tmp);
				carry = 0;
			}
		}
		
		if(carry == 1) res.push(1);				// 마지막에 carry가 존재 할 경우 스택에 1 push
		
		StringBuilder sb = new StringBuilder();
		while(!res.isEmpty()) sb.append(res.pop());		// 숫자를 역순으로 뽑으며 버퍼에 담아줌
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
}
