package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 10809번 : 알파벳 찾기
 *
 *	@see https://www.acmicpc.net/problem/10809
 *
 */
public class Boj10809 {
	private static final int NONE = -1;
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] S = br.readLine().toCharArray();
		int[] alpha = new int[26];
		br.close();
		
		Arrays.fill(alpha, NONE);							// 알파벳 위치를 띄워줄 배열에 존재하지 않음(-1)으로 가득 채움
		
		for(int i = 0; i < S.length; i++){
			if(alpha[S[i] - 97] == NONE){				// 최초 알파벳이 등장한 위치만 체크
				alpha[S[i] - 97] = i;							// a의 아스키코드는 97이고 a는 0번 인덱스에 해당 위치가 들어가므로 97을 빼줌
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < alpha.length; i++){			// a~z까지 해당 알파벳이 존재 했는지 안했는지에 대한 데이터 값을 버퍼에 담아줌
			sb.append(alpha[i]).append(SPACE);
		}
		
		System.out.println(sb.toString());			// 결과값 한번에 출력
	}
}
