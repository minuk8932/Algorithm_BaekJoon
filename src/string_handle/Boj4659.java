package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 4659번: 비밀번호 발음하기
 *
 *	@see https://www.acmicpc.net/problem/4659/
 *
 */
public class Boj4659 {
	private static final String TERMINATE = "end";
	private static final String PRE = "<";
	private static final String AC = "> is acceptable.";
	private static final String NA = "> is not acceptable.";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String word = br.readLine();
			if(word.equals(TERMINATE)) break;		// 입력으로 end가 들어오면 반복문 종료
			
			boolean isCorrect = true;
			char comp = ' ';
			char spec = ' ';
			int cnt = 1, cnt1 = 0, cnt2 = 0;		// 문제의 조건들을 구분하기 위한 변수
			int[] alpha = new int[26];				// 문자열에 모음이 포함되었는지 확인하기 위한 배열
			
			for(char tmp : word.toCharArray()) {
				if(comp == tmp) {		// 같은 문자가 2개이상 나오는지 확인하기
					cnt++;
				}
				else {			// 이전과 다른 문자가 들어오면 카운트 초기화 및 비교 변수를 현재 문자로 초기화
					cnt = 1;
					comp = tmp;
				}
				
				spec = tmp;		// 모음이나 자음이 3개씩 들어온 경우가 있는지 구분하는 변수
				
				if(spec == 'a' || spec == 'e' || spec == 'i' || spec == 'o' || spec == 'u') {	// 모음이 연속으로 들어오면
					cnt2 = 0;
					cnt1++;		// 카운트1 +1, 카운트2 초기화
				}
				else {			// 자음이 연속으로 들어오면
					cnt1 = 0;
					cnt2++;		// 카운트2 +1, 카운트1 초기화
				}
				
				if((cnt == 2 && tmp != 'e') && (cnt == 2 && tmp != 'o')) {		// 만약 카운트가 2이고, 그때의 문자열이 e, o가 아닌경우
					isCorrect = false;			// 거짓으로 변경 후 반복문 종료
					break;
				}
				
				if(cnt1 == 3 || cnt2 == 3) {		// 만약 카운트1 또는 카운트2가 3인경우
					isCorrect = false;			// 거짓으로 변경 후 반복문 종료
					break;
				}
				
				alpha[tmp - 97]++;		// 반복문이 진행중이라면 알파벳에 해당하는 인덱스에 +1
			}
			
			// 모음이 하나도 안들어온 경우
			if(alpha['a' - 97] == 0 && alpha['e' - 97] == 0 && alpha['i' - 97] == 0 && alpha['o' - 97] == 0 && alpha['u' - 97] == 0) isCorrect = false;
			
			// 조건에따라 결과값을 차례로 버퍼에 담은 후
			sb.append(PRE).append(word).append(isCorrect ? AC : NA).append(NEW_LINE);
		}
		
		// 결과값 한번에 출력
		System.out.println(sb.toString());
	}
}
