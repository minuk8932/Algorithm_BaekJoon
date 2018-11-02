package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16205번: 변수명
 *
 *	@see https://www.acmicpc.net/problem/16205/
 *
 */
public class Boj16205 {
	private static final String NEW_LINE = "\n";
	private static final char UNDER_BAR = '_';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		String line = st.nextToken();
		char[] param = line.toCharArray();
		
		StringBuilder sb = new StringBuilder();
		
		switch (N) {
		case 1:									// camel
			sb.append(line).append(NEW_LINE);
			
			for(char w: param) {
				if('A' <= w && w <= 'Z') {		// 대문자가 들어온 경우 앞에 언더바추가 후 대문자를 소문자로
					sb.append(UNDER_BAR);
					w = (char) (w + 32);
				}
				
				sb.append(w);
			}
			sb.append(NEW_LINE);
			
			// 가장 앞 문자를 대문자로 변경 후 나머지는 카멜식으로 버퍼에 저장
			sb.append((char)(line.charAt(0) - 32)).append(line.substring(1, line.length())).append(NEW_LINE);
			break;
			
		case 2:									// snake
			boolean isSkip = false;
			
			for(char w: param) {
				if(w == UNDER_BAR) {		// 언더바가 나온경우 스킵 변수를 참으로 바꾼후 연속 실행
					isSkip = true;
					continue;
				}
				
				if(isSkip) {				// 언더바에 의해 스킵 변수가 참이 된 경우
					isSkip = false;			// 거짓으로 바꾸고
					sb.append((char) (w - 32));		// 현 알파벳을 대문자로 변경
					
					continue;
				}
				
				sb.append(w);
			}
			
			String tmp = sb.toString();
			sb.append(NEW_LINE);
			
			sb.append(line).append(NEW_LINE);
			
			sb.append((char)(tmp.charAt(0) - 32)).append(tmp.substring(1, tmp.length())).append(NEW_LINE);
			break;
			
		case 3:									// pascal, 위 경우와 같은 메커니즘
			sb.append((char)(line.charAt(0) + 32)).append(line.substring(1, line.length())).append(NEW_LINE);
			
			for(int i = 0; i < param.length; i++) {
				if(i == 0) {
					sb.append((char) (param[i] + 32));
				}
				else {
					if('A' <= param[i] && param[i] <= 'Z') {
						sb.append(UNDER_BAR);
						param[i] = (char) (param[i] + 32);
					}
					
					sb.append(param[i]);
				}
			}
			sb.append(NEW_LINE);
			
			sb.append(line).append(NEW_LINE);
			break;
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
}
