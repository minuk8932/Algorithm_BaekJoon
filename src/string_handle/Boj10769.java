package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10769번 : 행복한지 슬픈지
 *
 * 	@see https://www.acmicpc.net/problem/10769
 * 
 */
public class Boj10769 {	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] msg = br.readLine().toCharArray();
		
		int cnt = 0;
		int uncnt = 0;
		
		for(int i = 0; i < msg.length; i++){
			if(i > 1){
				if(msg[i] == ')'){							// :-) 가 있는지 체크
					if(msg[i - 1] == '-'){
						if(msg[i - 2] == ':'){
							cnt++;
						}
					}
				}
				else if(msg[i] == '('){					// :-( 가 있는지 체크
					if(msg[i - 1] == '-'){
						if(msg[i - 2] == ':'){
							uncnt++;
						}
					}
				}
			}
		}
		
		if(cnt != 0 || uncnt != 0){							// 이모티콘이 하나라도 존재한 경우
			if(cnt > uncnt){									// :-) 가 많았을때
				System.out.println("happy");
			}
			else if(cnt < uncnt){							// :-( 가 많았을때
				System.out.println("sad");
			}
			else{													// 같은 수 였을때
				System.out.println("unsure");
			}
		}
		else {													// 이모티콘이 아예 없는 경우
			System.out.println("none");
		}
	}
}
