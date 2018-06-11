package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2386번: 도비의 영어공부
 *
 *	@see https://www.acmicpc.net/problem/2386/
 *
 */
public class Boj2386 {
	private static final char EXIT = '#';
	private static final char SPACE = ' ';
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			char[] words = br.readLine().toCharArray();
			
			if(words[0] == EXIT) break;			// 입력으로 '#'이 들어오면 반복문 종료
			
			int res = 0;
			char tmp = ' ';
			
			if(words[0] >= 'a' && words[0] <= 'z') {		// 기준문자가 a ~ z인 경우 tmp = A ~ Z
				tmp = (char) (words[0] - 32);
			}
			else {											// 반대의 경우
				tmp = (char) (words[0] + 32);
			}
			
			for(int i = 2; i < words.length; i++) {
				if(words[i] == SPACE) continue;					// 공백 입력은 넘기고
				
				if(words[i] == words[0] || words[i] == tmp) {	// 기준 문자와 같은 문자가 문장에 존재하면
					res++;										// res +1
				}
			}
			
			sb.append(words[0]).append(SPACE).append(res).append(NEW_LINE);		// 결과를 버퍼에 담은 후
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
