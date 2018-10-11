package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 4613번: QuickSum
 *
 *	@see https://www.acmicpc.net/problem/4613/
 *
 */
public class Boj4613 {
	private static final String TERMINATE = "#";
	private static final String NEW_LINE = "\n";
	private static final char SPACE = ' ';
	private static final char init = 'A';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = br.readLine();
			if(TERMINATE.equals(line)) break;		// '#' 입력시 종료
			
			int quickSum = 0;
			int seq = 1;
			
			for(char alpha: line.toCharArray()) {
				int num = alpha == SPACE ? 0 : (alpha - init) + 1;		// 입력이 ' '가 들어오면 num에 0을 알파벳은 'A' 부터 1로 설정하고 계산
				
				quickSum += seq++ * num;
			}
			
			sb.append(quickSum).append(NEW_LINE);			// 버퍼에 합산을 저장하고
		}
		
		System.out.println(sb.toString());			// 결과 값 한번에 출력
	}
}
