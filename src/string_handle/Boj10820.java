package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10820번: 문자열 분석
 *
 *	@see https://www.acmicpc.net/problem/10820/
 *
 */
public class Boj10820 {
	private static final String SPACE = " ";
	private static final String END_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		
		StringBuilder sb = new StringBuilder();
		
		while((line = br.readLine()) != null){			// 입력이 끝날때까지 반복문
			int[] count = new int[4];						// 0번 부터 차례로 알파벳 소문자/대문자, 숫자, 공백의 갯수를 담아줌
			
			for(char word: line.toCharArray()){	// 캐릭터 배열안의 값들을 하나씩 받아와서
				if(word == 32){								// 공백이면 3번
					count[3]++;
				}
				else if(word >= 48 && word <= 57){	// 숫자면 2번
					count[2]++;
				}
				else if(word >= 65 && word <= 90){	// 대문자면 1번
					count[1]++;
				}
				else{												// 소문자면 0번에 +1씩
					count[0]++;
				}
			}
			// 버퍼에 최종 결과 값들을 담고
			sb.append(count[0]).append(SPACE).append(count[1]).append(SPACE).append(count[2]).append(SPACE).append(count[3]).append(END_LINE);
		}
		
		// 결과값 한번에 출력
		System.out.println(sb.toString());
	}
}
