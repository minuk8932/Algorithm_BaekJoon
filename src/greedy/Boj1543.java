package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1543번: 문서 검색
 *
 *	@see https://www.acmicpc.net/problem/1543/
 *
 */
public class Boj1543 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] word = br.readLine().toCharArray();
		char[] comp = br.readLine().toCharArray();
		
		int res = 0;
		int increase = 0;
		
		for(int i = 0; i < word.length; i += increase) {
			int cnt = 0;

			for(int j = 0; j < comp.length; j++) {
				if(i + j >= word.length) break;		// 문서의 범위가 넘어가는 경우
				if(word[j + i] != comp[j]) break;	// 또는 문자 한개라도 다를 경우
				cnt++;
			}
			
			if(cnt == comp.length) {		// 같은 문자의 갯수가 동일하다면,
				res++;						// 문서 +1
				increase = comp.length;		// 증가 값에 비교 문자열의 길이를 담아줌
			}
			else {							// 문자 갯수가 다르다면
				increase = 1;				// 증가 값에 1
			}
		}
		
		System.out.println(res);			// 검색 한 문서 갯수 출력
	}
}
