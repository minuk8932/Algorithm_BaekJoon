package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15873번: Caesar Cipher
 *
 *	@see https://www.acmicpc.net/problem/15874/
 *
 */
public class Boj15874 {
	private static final char BAN1 = ' ';
	private static final char BAN2 = '.';
	private static final char BAN3 = ',';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int k = Integer.parseInt(st.nextToken()) % 26;		// 미리 알파벳 갯수의 나머지로 값을 변경
		int s = Math.abs(Integer.parseInt(st.nextToken()));
		char[] words = br.readLine().toCharArray();
		
		for(int i = 0; i < s; i++) {
			if(words[i] == BAN1 || words[i] == BAN2 || words[i] == BAN3) {	// 변형이 필요없는 경우
				sb.append(words[i]);
				continue;
			}
			
			if(words[i] >= 'A' && words[i] <= 'Z') {		// 대문자의 경우
				words[i] += k;
				
				if(words[i] > 'Z') words[i] -= 26;			// 연산 중 대문자 범위를 넘어가는 경우
			}
			
			else if(words[i] >= 'a' && words[i] <= 'z') {	// 소문자의 경우
				words[i] += k;
				
				if(words[i] > 'z') words[i] -= 26;			// 연산 중 소문자 범위를 넘어가는 경우
			}
			
			sb.append(words[i]);		// 계산 결과 단어를 버퍼에 담고
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
