package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10867번: 중복 빼고 정렬하기
 *
 *	@see https://www.acmicpc.net/problem/10867/
 *
 */
public class Boj10867 {
	private static final int INF = 1_001;
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		boolean[][] isPrint = new boolean[2][INF];
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(N-- > 0){
			int num = Integer.parseInt(st.nextToken());		// 숫자를 하나씩 가져옴
			
			int tmp = num < 0 ? -num : num;						// 음수 양수 체크 후
			
			if(isPrint[0][tmp] && num >= 0) continue;		// 음수배열이나 양수배열에 이미 true 초기화가 되었다면 다음으로 넘어감
			if(isPrint[1][tmp] && num < 0) continue;
			
			if(num >= 0){								// 양수의 경우 0의 해당 인덱스
				isPrint[0][num] = true;
			}
			else{											// 음수의 경우 1의 해당 인덱스
				isPrint[1][-num] = true;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = INF - 1; i > 0; i--){			// 음수는 가장 뒤의 수가 제일 작은수이므로, 뒤에서부터 버퍼에 담음
			if(isPrint[1][i]){
				sb.append(-i).append(SPACE);
			}
		}
		
		for(int i = 0; i < INF; i++){				// 양수는 차례로 버퍼에 담음
			if(isPrint[0][i]){
				sb.append(i).append(SPACE);
			}
		}
		
		System.out.println(sb.toString().trim());		// 결과값 한번에 출력
	}
}
