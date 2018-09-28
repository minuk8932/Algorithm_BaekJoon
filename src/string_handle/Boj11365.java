package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 11365번: 밀비 급일!
 *
 *	@see https://www.acmipc.net/problem/11365/
 *
 */
public class Boj11365 {
	private static final String TERMINATE = "END";
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = br.readLine();
			
			if(TERMINATE.equals(line)) break;		// END가 입력으로 들어오면 반복문 종료
			
			char[] word = line.toCharArray();
			for(int i = word.length - 1; i >= 0; i--) {		// 줄마다 버퍼에 값들을 거꾸로 담아줌
				sb.append(word[i]);
			}
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
}
