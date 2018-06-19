package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10174번: 팰린드롬
 *
 *	@see https://www.acmicpc.net/problem/10174/
 *
 */
public class Boj10174 {
	private static final String IS_PALINDROME = "Yes";
	private static final String IS_NOT_PALINDROME = "No";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		while(n-- > 0){
			char[] words = br.readLine().toLowerCase().toCharArray();	// 단어를 입력받고 영어일 경우 소문자로 모두 치환
			boolean isPalin = true;
			
			for(int i = 0; i < words.length / 2; i++) {
				if(words[i] != words[words.length - 1 - i]) {	// 배열하나씩 가져와서 앞뒤로 하나씩 비교
					isPalin = false;
				}
			}	
			
			sb.append(isPalin ? IS_PALINDROME : IS_NOT_PALINDROME).append(NEW_LINE);	// 참이면 yes 거짓이면 no를 버퍼에 저장
		}
		
		System.out.println(sb.toString());	// 결과값 한번에 출력
	}
}
