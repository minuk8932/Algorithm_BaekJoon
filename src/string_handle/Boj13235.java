package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 13235번: 팰린드롬
 *
 *	@see https://www.acmicpc.net/problem/13235/
 *
 */
public class Boj13235 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(isPalindrome(br.readLine()));	// 팰린드롬이면 true 아니면 false 출력
	}
	
	/**
	 * 팰린드롬 메소드
	 * 
	 */
	private static boolean isPalindrome(String input) {
		char[] word = input.toCharArray();
		
		for(int i = 0; i < word.length / 2; i++) {
			if(word[i] != word[word.length - 1 - i]) return false;	// 앞뒤로 문자를 비교해 다른것이 있으면 거짓을 반환
		}
		
		return true;		// 반복문이 종료되지 않은 경우 참 반환
	}
}
