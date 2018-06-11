package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 9226번: 도깨비말
 *
 *	@see https://www.acmicpc.net/problem/9226/
 *
 */
public class Boj9226 {
	private static final String GOBLIN = "ay";
	private static final String NEW_LINE = "\n";
	private static final char EXIT = '#';
	private static final char[] VOW = {'a', 'e', 'i', 'o', 'u'};
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			char[] words = br.readLine().toCharArray();
			
			if(words[0] == EXIT) break;				// 입력으로 #가 들어오면 반복문 종료
			
			int idx = -1;
			
			for(int i = 0; i < words.length; i++) {
				for(int j = 0; j < VOW.length; j++) {		// 배열마다 단어에 모음이 존재하는지 확인 후
					if(words[i] == VOW[j]) {
						idx = i;						// 존재하는경우 idx 값에 담아줌
						
						break;
					}
				}
				
				if(idx != -1) break;				// idx 값이 갱신된 경우 반복문 종료
 			}
			
			if(idx == -1) idx = 0;					// idx가 갱신이 아예 안된경우
			
			for(int i = idx; i < words.length; i++) {	// idx 기준 배열 뒷부분을 먼저 버퍼에 담고
				sb.append(words[i]);
			}
			
			for(int i = 0; i < idx; i++) {		// idx 이전의 단어들을 이후에 버퍼에 담아줌
				sb.append(words[i]);
			}
			
			sb.append(GOBLIN).append(NEW_LINE);		// 도깨비 언어를 뒤에 붙여줌
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
