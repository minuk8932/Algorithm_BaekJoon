package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1718번: 암호
 *
 *	@see https://www.acmicpc.net/problem/1718/
 *
 */
public class Boj1718 {
	private static final char SPACE = ' ';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] words = br.readLine().toCharArray();		// 암호화 시킬 단어
		char[] aes = br.readLine().toCharArray();			// 암호화 키
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < words.length; i++){
			if(words[i] == SPACE){					// 공백이 들어온 경우
				sb.append(SPACE);					// 공백을 출력버퍼에 담고 다음 단어로 넘어감
				
				continue;
			}
			
			int alpha = words[i] - aes[i % aes.length];		// 단어와 암호키의 차이를 구한 후
			
			if(alpha > 0){									// 0보다 크면
				sb.append((char) (alpha + 96));	// a부터 순서대로 값을 더해 문자형으로 변환 후 버퍼에 담음
			}
			else if(alpha < 0){							// 0보다 작은경우 z부터 역행으로 값을 더하여 문자형 변환 후 버퍼에 담음
				sb.append((char) (122 + alpha));
			}
			else{												// 두 문자의 차가 같은 경우
				sb.append('z');
			}
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
