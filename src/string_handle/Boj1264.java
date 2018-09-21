package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1264번: 모음의 개수
 *
 *	@see https://www.acmicpc.net/problem/1264/
 *
 */
public class Boj1264 {
	private static final String TERMINATE = "#";
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = br.readLine();
			if(TERMINATE.equals(line)) break;		// #이 들어오는 경우 반복문 종료
			
			int cnt = 0;
			
			for(char c: line.toCharArray()) {			// 모음의 갯수를 세고
				if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') cnt++;
				if(c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') cnt++;
			}

			sb.append(cnt).append(NEW_LINE);		// 각 줄마다 갯수를 버퍼에 담음
		}
		
		System.out.println(sb.toString());			// 결과 값 한번에 출력
	}
}
