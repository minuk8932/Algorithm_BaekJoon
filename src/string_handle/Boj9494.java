package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 9494번: 데구르르르
 *
 *	@see https://www.acmicpc.net/problem/9494/
 *
 */
public class Boj9494 {
	private static final char SPACE = ' ';
	private static final String NEW_LINE = "\n";
	
	private static char[][] sentence = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true){
			int n = Integer.parseInt(br.readLine());
			if(n == 0) break;										// 입력으로 0이 들어올 경우 프로그램 종료
			
			sentence = new char[n][100];
			
			for(int i = 0; i < n; i++){
				sentence[i] = br.readLine().toCharArray();
			}
			
			int drop = 0;							// 공이 떨어지는 인덱스를 담을 변수
			
			for(int i = 0; i < n; i++){
				boolean isVisited = false;
				
				for(int j = drop; j < sentence[i].length; j++){
					if(sentence[i][j] == SPACE){							// 각 줄마다 공백이 있는 위치를 체크, 이때 이전 줄의 공백보다 뒤에있는 공백을 찾아야함
						drop = j;
						isVisited = true;						// 공백을 찾은 경우 참으로 방문 변수를 바꿔주고
						
						break;								// 다음 줄로 넘어감
					}
				}
				
				if(!isVisited && drop < sentence[i].length){		// 만약 어느 한 줄에서 공백이 존재하지 않았고, 이전의 공백 수치가 현재 줄의 길이보다 짧은 경우
					drop = sentence[i].length;						// 낙하 수치를 줄의 길이로 갱신, 즉 해당 줄의 맨뒤에서 공이 떨어짐
				}
			}
			
			sb.append(++drop).append(NEW_LINE);		// 시작은 1부터 이므로 drop 값을 +1 하여 버퍼에 담음
		}
		
		System.out.println(sb.toString());			// 결과값 한번에 출력
	}
}
