package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12778번: CTP공국으로 이민가자
 *
 *	@see https://www.acmicpc.net/problem/12778/
 *
 */
public class Boj12778 {
	private static final String C_TO_I = "N";
	private static final String I_TO_C = "C";
	private static final char SPACE = ' ';
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());		// 들어오는 문자의 수
			String command = st.nextToken();			// 바꾸는 조건
			
			if(command.equals(C_TO_I)) {				// 숫자를 문자로
				st = new StringTokenizer(br.readLine());
				
				for(int i = 0; i < M; i++) {
					int num = Integer.parseInt(st.nextToken());
					sb.append((char) (num + 64)).append(SPACE);		// 변환 결과를 버퍼에 담아줌
				}
			}
			else if(command.equals(I_TO_C)) {				// 문자를 숫자로
				char[] alpha = br.readLine().toCharArray();
				for(int i = 0; i < alpha.length; i++) {
					if(alpha[i] != SPACE) {
						sb.append(alpha[i] - 64).append(SPACE);		// 변환 결과를 버퍼에 담아줌
					}
				}
			}
			
			sb.append(NEW_LINE);		// 케이스별로 개행 삽입
		}
		
		System.out.println(sb.toString());	// 결과값 한번에 출력
	}
}
