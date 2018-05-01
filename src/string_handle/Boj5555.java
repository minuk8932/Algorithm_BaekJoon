package string_handle;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * 
 * 	@author minchoba
 *	백준 5555번: 반지
 *
 *	@see https://www.acmicpc.net/problem/5555/
 *
 */
public class Boj5555 {	
	private static int result = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String words = br.readLine();					// 체크 할 문자
		
		int N = Integer.parseInt(br.readLine());	// 반지의 갯수
		
		while(N-- > 0){
			StringBuilder sb = new StringBuilder();		// 테스트 케이스마다 버퍼를 비워줌
			String ring = br.readLine();						// 반지의 문자열
			
			sb.append(ring).append(ring);				// 반지의 길이는 최대 10, 검사할 문자열도 최대 10 따라서 반지의 문자열을 버퍼내에서 붙여서 검사
			if(sb.toString().contains(words)){		// 버퍼내의 값이 체크할 문자열을 포함 하는가?
				result++;						// 포함하는 경우 +1
			}
		}
		
		System.out.println(result);		// 결과 값 출력
	}
}
