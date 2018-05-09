package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2675번: 문자열 반복
 *
 *	@see https://www.acmicpc.net/problem/2675/
 *
 */
public class Boj2675 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			String S = st.nextToken();
			
			for(char word : S.toCharArray()){		// 문자열 하나 받아와서 문자하나하나 짤라서
				for(int i = 0; i < R; i++){					// R개 만큼 반복해서
					sb.append(word);						// 버퍼에 하나씩 담아줌
				}
			}
			
			sb.append(NEW_LINE);				// 개행 문자열 삽입
		}
		
		System.out.println(sb.toString());		// 한번에 결과값 출력
	}
}
