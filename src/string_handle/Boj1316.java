package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1316번 : 그룹 단어 체커
 *
 *	@see https://www.acmicpc.net/problem/1316
 *
 */
public class Boj1316 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		
		while(N-- > 0){														// 테스트 케이스 수 만큼 실행
			boolean[] alpha = new boolean[26];
			char[] words = br.readLine().toCharArray();
			
			for(int i = 0; i < words.length; i++){				
				if(i > 0){														// Arrays out of bounds exception 방지
					if(words[i - 1] != words[i]){							// 이전 단어와 현재 단어가 다르면서
						if(alpha[words[i] - 97]){							// 현재 그 단어가 이전에 나왔던 단어인가?, 즉 그룹단어가 한번 나왔었는지 확인
							break;												// 나온 단어라면 반복문 종료
						}
					}
				}
				
				if(i == words.length - 1){									// 만약 끝까지 그룹 단어 였을 경우 참이므로 카운트 수 1 증가
					cnt++;
				}
				
				alpha[words[i] - 97] = true;							// 그룹단어가 나왔음을 체크
			}
		}
		System.out.println(cnt);											// 테스트 케이스 중 그룹 단어의 갯수 출력
	}
}
