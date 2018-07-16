package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 15904번: UCPC는 무엇의 약자일까?
 *
 *	@see https://www.acmicpc.net/problem/15904/
 *
 */
public class Boj15904 {
	private static final char[] UCPC = {'U', 'C', 'P', 'C'};
	
	private static final String POSSIBLE = "I love UCPC";
	private static final String IMPOSSIBLE = "I hate UCPC";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean[] res = new boolean[4];
		int idx = 0;
		
		for(char word : br.readLine().toCharArray()) {
			if(idx == 4) break;		// UCPC를 모두 찾았으면 종료
			
			if(word == UCPC[idx]) {	// 배열에 해당하는 값을 순서대로 탐색
				res[idx] = true;		// 있는 경우 참으로 값 변경
				idx++;
			}
		}
		
		boolean isTrue = true;
		
		for(int i = 0; i < res.length; i++) {
			if(!res[i]) {			// 만약 res에 false가 존재하는 경우 isTrue에 거짓을 넣고 반복문 종료
				isTrue = false;
				break;
			}
		}
		
		System.out.println(isTrue ? POSSIBLE : IMPOSSIBLE);		// 결과 값 출력
	}
}
