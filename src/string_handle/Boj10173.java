package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10173번: 니모를 찾아서
 *
 *	@see https://www.acmicpc.net/problem/10173/
 *
 */
public class Boj10173 {
	private static final String TERMINATE = "EOI";
	private static final String NEMO = "NEMO";
	private static final String YES = "Found";
	private static final String NO = "Missing";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = br.readLine();
			if(line.equals(TERMINATE)) break;	// EOI가 입력으로 들어오면 종료
			
			line = line.toUpperCase();			// EOI가 없을 경우 대문자로 변경
			sb.append(line.contains(NEMO) ? YES : NO).append(NEW_LINE);		// 니모가 있다면 Found 없다면, Missing을 버퍼에 담아줌
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
