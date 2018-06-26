package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 11091번: 알파벳 전부 쓰기
 *
 *	@see https://www.acmicpc.net/problem/11091/
 *
 */
public class Boj11091 {
	private static final String PANGRAM = "pangram";
	private static final String NOT_PANGRAM = "missing ";
	private static final String NEW_LINE = "\n";
	private static final char SPACE = ' ';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			boolean[] alpha = new boolean[26];
			
			for(char tmp : br.readLine().toCharArray()) {	// 차례로 들어오는 문자를 검사
				if(tmp == SPACE) continue;		// 공백인 경우 다음 문자로
				
				if(tmp >= 'a' && tmp <= 'z') {	// 소문자랑 대문자는 각각 배열값에 맞게 계산 후 배열을 참으로 변경
					alpha[tmp - 97] = true;
				}
				
				if(tmp >= 'A' && tmp <= 'Z') {
					alpha[tmp - 65] = true;
				}
			}
			
			boolean isPan = true;
			for(int i = 0; i < alpha.length; i++) {	// 진리배열 전체를 검사
				if(!alpha[i]) {				// 만약 등장하지 않은 알파벳이 있다면,
					if(isPan) {				// 그리고 처음으로 등장했다면
						isPan = false;			// 팬그램인가?를 거짓으로 바꾸고
						sb.append(NOT_PANGRAM);	// 접두어(missing)를 먼저 버퍼에 담고
					}
					
					sb.append((char)(i + 97));	// 버퍼에 해당 알파벳을 차례로 담아줌
				}
			}
			
			if(isPan) {
				sb.append(PANGRAM);		// 만약 팬그램인가?가 참이면, pangram을 버퍼에 담아줌
			}
			
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb.toString());	// 결과 값 한번에 출력
	}
}
