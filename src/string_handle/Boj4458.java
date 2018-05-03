package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 4458번: 첫 글자를 대문자로
 *
 *	@see https://www.acmicpc.net/problem/4458/
 *
 */
public class Boj4458 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(N-- > 0){
			String line = br.readLine();						// 한줄씩 문자열을 받아
			
			int first = 0;								// 첫글자 구분용 정수 초기화
			for(char word : line.toCharArray()){			// 향상된 포문을 이용해 한 글자씩 가져온다
				if(first == 0){											// 첫글자의 경우
					first++;												// 구분 정수 + 1

					if(word >= 'A' && word <= 'Z'){		// 만약 첫 글자가 본래 대문자라면
						sb.append(word);						// 그대로 버퍼에 담고 다음 문자로 넘어감
						continue;
					}
					
					sb.append((char)(word - 32));		// 소문자일 경우엔 연산을통해 대문자로 바꿔줌 (ASCII code)
				}
				else{
					sb.append(word);					// 맨 앞자리가 아닌경우 그대로 버퍼에 담아줌
				}
			}
			
			sb.append(NEW_LINE);						// 각 라인별로 개행 문자 삽입
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}
