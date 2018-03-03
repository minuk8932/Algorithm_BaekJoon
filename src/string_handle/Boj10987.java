package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10987번 : 모음의 개수
 *
 *	@see https://www.acmicpc.net/problem/10987
 *
 */
public class Boj10987 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] word = br.readLine().toCharArray();
		int cnt = 0;
		
		for(int i = 0; i < word.length; i++){
			if(word[i] == 'a' || word[i] == 'e' || word[i] == 'i' || word[i] == 'o' || word[i] == 'u'){	// 모음이 존재하는지 체크
				cnt++;
			}
		}
		
		System.out.println(cnt);		// 모음개수 출력
	}
}
