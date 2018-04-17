package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9093번 : 단어 뒤집기
 *
 *	@see https://www.acmicpc.net/problem/9093/
 *
 */
public class Boj9093 {
	private static final String SPACE = " ";
	private static final String END_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);	// 한 단어씩 자르고
			
			while(st.hasMoreTokens()){									// 자른 단어가 존재할때 까지 반복문
				String line = st.nextToken();							// 자른 한 단어를 받아와서
				int leng = line.length();
				
				for(int i = leng - 1; i >= 0; i--){							// 얻어온 길이를 통해 단어를 역순으로 버퍼에 담음
					sb.append(line.charAt(i));
				}
				sb.append(SPACE);						// 한 단어가 끝나면 공백 추가
			}
			
			sb.append(END_LINE);						// 한 문장이 끝나면 줄바꿈 추가
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
