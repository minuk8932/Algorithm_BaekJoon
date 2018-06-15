package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 15239번: Password check
 *
 *	@see https://www.acmicpc.net/problem/15239/
 *
 */
public class Boj15239 {
	private static final String pwSet = "+_)(*&^%$#@!./,;{}";
	private static final String YES = "valid";
	private static final String NO = "invalid";
	private static final String NEW_LINE = "\n";
	
	private static final int MIN_LENGTH = 0;
	private static final int LOWER = 1;
	private static final int UPPER = 2;
	private static final int DIGIT = 3;
	private static final int HAS_SET = 4;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());	// 입력될 비밀번호의 길이
			String line = br.readLine();				// 비밀번호를 받아옴
			boolean[] isCorrect = new boolean[5];		// 각 조건마다 옳바른지 확인 할 배열
			
			if(N < 12) {							// 길이가 12보다 짧은경우, 비밀번호로 적절치 못함
				sb.append(NO).append(NEW_LINE);
			}
			else {								// 12보다 긴 경우
				isCorrect[MIN_LENGTH] = true;
				
				for(char tmp : line.toCharArray()) {	// 입력으로 들어온 문자열을 문자 배열로 하나씩 꺼내와서
					if(tmp >= 'a' && tmp <= 'z') {		// 해당 문자에 소문자가 포함되었는가?
						if(!isCorrect[LOWER]) {
							isCorrect[LOWER] = true;
						}
					}
					
					if(tmp >= 'A' && tmp <= 'Z') {		// 해당 문자에 대문자가 포함되었는가?
						if(!isCorrect[UPPER]) {
							isCorrect[UPPER] = true;
						}
					}
					
					if(tmp >= '0' && tmp <= '9') {		// 해당 문자에 숫자가 하나라도 있는가?
						if(!isCorrect[DIGIT]) {
							isCorrect[DIGIT] = true;
						}
					}
					
					if(pwSet.contains(String.valueOf(tmp))) {	// 해당 문자가 특수문자열 내에 포함되는가? 
						if(!isCorrect[HAS_SET]) {
							isCorrect[HAS_SET] = true;
						}
					}
				}				
				
				// 위의 모든 5가지 경우를 포함한다면 valid, 하나도 포함 못한다면 invalid를 버퍼에 담아줌
				sb.append(isCorrect[MIN_LENGTH] && isCorrect[LOWER] && isCorrect[UPPER] && isCorrect[DIGIT] && isCorrect[HAS_SET]
						? YES : NO).append(NEW_LINE);
			}
		}
		
		System.out.println(sb.toString());	// 결과값 한번에 출력
	}
}
