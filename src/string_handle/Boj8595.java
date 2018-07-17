package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 8595번: 히든 넘버
 *
 *	@see https://www.acmicpc.net/problem/8595/
 *
 */
public class Boj8595 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 계산
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		char[] line = br.readLine().toCharArray();
		
		String num = "";
		long res = 0;
		
		for(char tmp : line) {
			if((tmp >= 'a' && tmp <= 'z') || (tmp >= 'A' && tmp <= 'Z')) {	// 알파벳이 들어오는 경우
				if(!"".equals(num)) {				// 만약 num에 값이 있다면
					res += Long.parseLong(num);	// 형 변환 후 res에 합연산
					num = "";						// 이후 초기화
				}
				continue;
			}
			
			num += tmp;			// 숫자인 경우 num에 문자열 형태로 하나씩 붙여줌
		}
		
		if(!"".equals(num)) res += Long.parseLong(num);		// 아직 남은 숫자가 있다면 res에 다시 더한 후
		
		System.out.println(res);		// 결과 값 출력
	}
}
