package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 9971번 : The Hardest Problem Ever
 *
 *	@see https://www.acmicpc.net/problem/9971/
 *
 */
public class Boj9971 {
	private static final String START = "START";
	private static final String END = "END";
	private static final String TERMINATE = "ENDOFINPUT";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true){
			String line = br.readLine();
			
			if(line.equals(TERMINATE)){			// ENDOFINPUT이 입력으로 들어올 경우 반복문 종료
				break;
			}
			
			if(line.equals(START)){				// START가 입력으로 들어올 경우 다음으로 넘어가 반복 수행
				continue;
			}
			
			if(line.equals(END)){					// END가 입력으로 들어올 경우 다음으로 넘어가 반복 수행
				continue;
			}
			
			for(char word : line.toCharArray()){		// 입력으로 들어온 문장을 문자 배열로 변환 후 향상된 for문을 이용해 하나씩 가져와서 연산을 실행
				if(word >= 'A' && word <= 'Z'){
					sb.append(converter(word));			// 들어온 word의 값이 알파벳 범위내에 존재 할 경우 converter 함수 실행
				}
				else{
					sb.append(word);							// 알파벳이 아닌경우 출력 버퍼에 바로 담아줌
				}
			}
			sb.append(NEW_LINE);								// 문장별로 나누기 위해 끝에 개행문자 삽입
		}
		
		System.out.println(sb.toString());				// 결과값 한번에 출력
	}
	
	/**
	 * 
	 * 	@param word : 알파벳 범위내에 있는 문자
	 * 	@return : 변환된 word
	 */
	private static char converter(char word){
		if(word >= 'A' && word <= 'E'){	// A~E 내의 알파벳인 경우 +21
			word = (char) (word + 21);
		}
		else{											// 그 외 -5
			word = (char) (word - 5);
		}
		
		return word;
	}
}
