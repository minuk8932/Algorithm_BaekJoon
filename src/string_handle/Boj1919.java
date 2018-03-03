package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1919번 : 애너그램 만들기
 *
 *	@see https://www.acmicpc.net/problem/1919
 *
 */
public class Boj1919 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();
		boolean[] chker1 = new boolean[str1.length];					// 각 알파벳을 방문했는지 검사할 진리 배열
		boolean[] chker2 = new boolean[str2.length];
		int cnt = 0;
		
		for(int i = 0; i < str1.length; i++){
			for(int j = 0; j < str2.length; j++){
				if(str1[i] == str2[j]){												// 두 단어가 같을 때
					if(!chker1[i] && !chker2[j]){									// 둘다 체크 안되어있는 경우
						chker1[i] = true;												// 두 단어 모두 체크함으로 변경
						chker2[j] = true; 
						cnt++;
					}
				}
			}
		}
		
		System.out.println(str1.length - cnt + str2.length - cnt);	// 결과값 출력
		
	}
}
