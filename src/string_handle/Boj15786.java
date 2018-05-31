package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15786번: send me the money
 *
 *	@see https://www.acmicpc.net/problem/15786/
 *
 */
public class Boj15786 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		char[] words = br.readLine().toCharArray();		// 기준 문자열 입력
		
		while(M-- > 0) {
			char[] comp = br.readLine().toCharArray();	// 비교 문자열 입력
			boolean isPossible = false;
			
			if(N > comp.length) {						// 비교 문자열이 짧을 경우
				sb.append(isPossible).append(NEW_LINE);	// 불가능을 버퍼에 다음
			}
			else {
				int idx = 0;							// 기준 문자열의 인덱스
				
				for(int i = 0; i < comp.length; i++) {
					if(idx == N) break;				// 문자열 비교가 완료되었다면 바로 종료
					
					if(words[idx] == comp[i]) {		// 같은 문자가 있다면
						idx++;					// 비교 해야 할 기준 문자열 인덱스를 뒤로 한칸 미룸
					}
				}
				
				if(idx == N) {							// 비교가 문제없이 완료된 경우
					sb.append(!isPossible).append(NEW_LINE);
				}
				else {									// 비교가 완료되지 못한 경우
					sb.append(isPossible).append(NEW_LINE);
				}
			}
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
