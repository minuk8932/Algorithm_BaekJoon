package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 4740번: 거울, 오 거울
 *
 *	@see https://www.acmicpc.net/problem/4740/
 *
 */
public class Boj4740 {
	private static final String EXIT = "***";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = br.readLine();
			
			if(EXIT.equals(line)) break;			// ***가 입력으로 들어오면 반복문 종료
			
			int leng = line.length();
			for(int i = leng - 1; i >= 0; i--) {	// 입력받은 문자열을 역순으로 버퍼에 담아줌
				sb.append(line.charAt(i));
			}
			
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
