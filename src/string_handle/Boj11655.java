package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 11655번 : ROT13
 *
 *	@see https://www.acmicpc.net/problem/11655/
 *
 */
public class Boj11655 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		
		StringBuilder sb = new StringBuilder();
		
		for(char word : S.toCharArray()){							// 문자열을 문자 배열에 담고 향상된 포문으로 하나씩 가져와서
			if(word >= 'A' && word <= 'Z'){							// 해당 문자가 알파벳 대문자 범위일 경우
				int ctoi = word + 13;										// 13칸 뒤로 밀었을때의 정수 값을 구한 후
				
				sb.append(ctoi >= 65 && ctoi <= 90 ? (char)ctoi : (char)(ctoi - 26));	// 밀린경우에 아직 대문자 알파벳 범위 내이면 버퍼에 담고 범위를 넘어가면 다시 대문자 에이부터 시작
			}
			else if (word >= 'a' && word <= 'z'){						// 해당 문자가 알파벳 소문자 범위일 경우
				int ctoi = word + 13;										// 13칸 뒤로 밀었을때의 정수 값을 구한 후
				
				sb.append(ctoi >= 97 && ctoi <= 122 ? (char)ctoi : (char)(ctoi - 26));	// 밀린경우에 아직 소문자 알파벳 범위 내이면 버퍼에 담고 범위를 넘어가면 다시 소문자 에이부터 시작
			}
			else{
				sb.append(word);											// 알파벳이 아닌경우 바로 버퍼에 담음
			}
		}
		
		System.out.println(sb.toString());			// 결과값 한번에 출력
	}
}
