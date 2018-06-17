package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11094번: 꿍 가라사대
 *
 *	@see https://www.acmicpc.net/problem/11094
 *
 */
public class Boj11094 {
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String[] words = new String[st.countTokens()];
			
			boolean heSays = false;
			
			for(int i = 0; i < words.length; i++) {
				words[i] = st.nextToken();
				
				// 명령이 붙었으면 heSays를 참으로 바꾸고 다음 인덱스로 넘어감
				if(i > 0 && "Simon".equals(words[0]) && "says".equals(words[1]) && !heSays) {
					heSays = true;
					
					continue;
				}

				if(heSays) {			// heSays: 참이라면 버퍼에 해당 값을 담아줌
					sb.append(SPACE).append(words[i]);
				}
			}
			
			if(heSays) sb.append(NEW_LINE);	// heSays: 참인 경우만 개행
		}
		
		System.out.println(sb.toString());	// 결과값 한번에 출력
	}
}
