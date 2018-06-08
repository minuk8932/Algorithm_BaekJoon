package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 3568번: iSharp
 *
 *	@see https://www.acmicpc.net/problem/3568/
 *
 */
public class Boj3568 {
	private static final String SPLIT = ", ";
	private static final String SPACE = " ";
	private static final String NEW_LINE = ";\n";
	private static final char EXIT = ';';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		
		StringBuilder sb = new StringBuilder();		
		StringTokenizer st = new StringTokenizer(line, SPLIT);
		String stmt = st.nextToken();
		
		while(true) {
			char[] word = st.nextToken().toCharArray();
			
			sb.append(stmt);		// 미리 뽑아둔 자료형 선언을 앞에 붙이고
			
			for(int i = word.length - 1; i >= 0; i--) {		// 반복문을 역순으로 돌려가며
				if(word[i] == '[')	word[i] = ']';		// 선언 다음으로 괄호가 나오면 괄호는 서로 역방향으로 바꿔주고
				else if(word[i] == ']') word[i] = '[';
				
				if(word[i] == EXIT) continue;			// 세미콜론은 제외
				if((word[i] >= 'a' && word[i] <= 'z') || (word[i] >= 'A' && word[i] <= 'Z')) continue;		// 알파벳(변수명) 또한 제외
				
				sb.append(word[i]);		// 한 나머지 *, & [, ]를 버퍼에 담아줌
			}
			
			sb.append(SPACE);		// 변수 명 선언을 위한 공백 추가 후
			
			for(int i = 0; i < word.length; i++) {		// 정방향으로 반복문 실행하며, 알파벳만 골라서
				if((word[i] >= 'a' && word[i] <= 'z') || (word[i] >= 'A' && word[i] <= 'Z')) {
					sb.append(word[i]);		// 버퍼에 담아줌(변수명 선언)
				}
			}
			
			sb.append(NEW_LINE);		// 테스트케이스 마다 개행문자 및 세미콜론 추가
			
			if(word[word.length - 1] == EXIT) break;	// 세미콜론이 배열 끝에 존재할 경우 반복문 종료
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
