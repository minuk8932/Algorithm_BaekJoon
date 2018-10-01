package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 14954번: Happy Number
 *
 *	@see https://www.acmicpc.net/problem/14954/
 *
 */
public class Boj14954 {
	private static final String H = "HAPPY";
	private static final String U = "UNHAPPY";
	
	private static final int INF = 1_001;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String num = br.readLine();
		boolean[] isLoop = new boolean[INF];
		
		int leng = num.length();
		int res = Integer.parseInt(num);
		
		if(res < INF) isLoop[res] = true;		// 숫자가 작은 경우 미리 등장한 것으로 표시
		
		boolean isHappy = true;
		String tmp = num;
		int func = res;
		
		while(func != 1) {
			func = 0;
			
			for(int i = 0; i < leng; i++) {
				int one = (tmp.charAt(i) - '0');		// 각자리의 제곱수를 더하는 연산
				func += one * one;
			}
			
			if(isLoop[func]) {		// 해당 수가 1이 아니면서 이전에 나온경우
				isHappy = false;	// 언해피한 숫자로 체크하고 반복문 종료
				break;
			}
			
			isLoop[func] = true;		// 그 외, 해당 숫자에 대한 배열을 참으로 변경
			
			tmp = String.valueOf(func);		// 다음 반복문을 위해 다시 tmp에 문자열로 바꾸어 넣고
			leng = tmp.length();			// 길이를 뽑아냄
		}
		
		System.out.println(isHappy ? H : U);		// 행복한 경우 HAPPY를 안 행복한 경우 UNHAPPY 출력
	}
}
