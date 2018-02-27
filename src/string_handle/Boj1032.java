package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1032번 : 명령 프롬프트
 *	
 *	@see https://www.acmicpc.net/problem/1032
 *
 */
public class Boj1032 {
	private static final char RAND = '?';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char[][] cmd = new char[N][50];
		for(int i = 0; i < N; i++){
			cmd[i] = br.readLine().toCharArray();
		}
		
		StringBuilder sb = new StringBuilder();
		
		int[] sameChk = new int[cmd[0].length];
		
		for(int i = 0; i <cmd[0].length; i++){		// 각 배열마다 같은 단어가 있는지 확인하기위한 반복문
			for(int j = 0; j < N; j++){
				if(j > 0){
					if(cmd[j][i] == cmd[j - 1][i]){
						sameChk[i]++;						// 값이 같다면 +1
					}
					else{
						sameChk[i]--;							// 값이 다르다면 -1
					}
				}
			}
		}
		
		for(int i = 0; i < cmd[0].length; i++){
			// 만약 sameChk[i]의 값이 N-1과 같다면, 이는 단어의 각 알파벳 열을 비교했을때 모두 같은 알파벳이 존재하는지 확인 할 수 있음			
			
			if(sameChk[i] == N - 1){						// 서로 모두 같은가?
				sb.append(cmd[0][i]);						// 그렇다면 제대로된 단어를 버퍼로 넘겨줌
			}
			else{
				sb.append(RAND);							// 다른 단어가 하나라도 있다면 ?를 버퍼로 넘겨줌
			}
		}
		
		System.out.println(sb.toString());			// 결과값 한번에 출력
	}
}
