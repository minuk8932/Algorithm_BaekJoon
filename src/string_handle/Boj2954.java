package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2954번: 창영이의 일기장
 *
 *	@see https://www.acmicpc.net/problem/2954/
 *
 */
public class Boj2954 {	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] words = br.readLine().toCharArray();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < words.length; i++){
			if(words[i]=='a' || words[i]=='i' || words[i]=='u' || words[i]=='e' || words[i]=='o'){	// 모음 등장시
				sb.append(words[i]);		// 해당 모음만 버퍼에 담은 후
				i+=2;					// 인덱스 2칸 패스
				continue;
			}
			
			sb.append(words[i]);		// 모음이 아닌 경우 그냥 버퍼에 담음
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
