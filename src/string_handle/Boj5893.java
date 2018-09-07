package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

/**
 * 
 * 	@author minchoba
 *	백준 5893번: 17배
 *
 *	@see https://www.acmicpc.net/problem/5893/
 *
 */
public class Boj5893 {
	private static final String EMPTY = "";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] N = br.readLine().toCharArray();
		char[] num17 = {'1', '0', '0', '0', '1'};
		
		if(N.length == 1) {									// 0, 1이 들어올 경우 조건문 처리
			System.out.println(N[0] == '0' ? 0 : "10001");
		}
		else {												// 그 외
			int size = N.length > num17.length ? num17.length : N.length;		// 반복문을 돌리기위해 기준이 될 길이를 찾음
			int longer = N.length < num17.length ? num17.length : N.length;
			
			String[] res = new String[size];
			Arrays.fill(res, EMPTY);			// 결과 배열초기화
			
			for(int i = size - 1; i >= 0; i--) {
				for(int j = 0; j < i + 1; j++) {	// 자릿수를 맞추기 위한 전처리
					res[j] += '0';
				}
				
				for(int j = longer - 1; j >= 0; j--) {		// 10001과 각 자릿 수를 곱한 값들을 배열에 담아줌
					res[i] += size == num17.length ? (char) (((num17[size - 1 - i] - '0') * (N[longer - 1 - j] - '0')) + '0') : 
													(char) (((num17[longer - 1 - j] - '0') * (N[size - 1 - i] - '0')) + '0');
				}
				
				for(int j = 0; j < size - 1 - i; j++) {		// 뒷자리 자리를 맞추기 위한 후처리
					res[size - 1 - i] += '0';
				}
			}
			
			Stack<Character> lifo = new Stack<>();
			
			int carry = 0;
			char[] over = res[0].toCharArray();
			char[] under = res[res.length - 1].toCharArray();
			
			for(int i = over.length - 1; i >= 0; i--) {			// 스택에 저장하기위한 프로세스
				int tmp = (over[i] - '0') + (under[i] - '0');	// 자릿수별로 합을 구하고
					
				if(carry == 0)	{				// 올림수가 없는 경우
					if(tmp == 2) {						// 올림수가 추가되어야 하는 경우
						carry++;
						
						lifo.push((char)(0 + '0'));
					}
					else {								// 올림수가 필요 없는 경우
						lifo.push((char)(tmp + '0'));
					}
				}
				else {
					if(tmp == 2) {						// 올림수가 추가되어야 하는 경우
						lifo.push((char)(1 + '0'));
					}
					else {
						carry--;
						tmp++;
						
						if(tmp == 2 || tmp == 3) {		// 올림수 중복 추가
							tmp = tmp == 2 ? 0 : 1;
							carry++;
						}
						
						lifo.push((char)(tmp + '0'));
					}
				}
			}
			
			String ans = "";
			while(!lifo.isEmpty()) {			// 스택에 담긴 결과값을 문자열에 저장
				ans += lifo.pop();
			}
			
			// 맨 앞자리에 0이 존재하면 제거하고, 그 외에는 그대로 결과 값 출력
			System.out.println(ans.charAt(0) == '0' ? ans.substring(1, ans.length()) : ans);
		}
	}
}
