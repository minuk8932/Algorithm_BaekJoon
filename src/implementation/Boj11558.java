package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author minchoba
 *	백준 11558번 : The game of death
 *
 *	@see http://www.acmicpc.net/problem/11558
 *
 */

public class Boj11558 {
	private static final String NEW_LINE = "\n";
	private static final int OVER = 10_001;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			int N = Integer.parseInt(br.readLine());
			int[] game = new int[N + 1];
			int idx = 1, cnt = 0;
			
			for(int i = 1; i < N + 1; i++){
				game[i] = Integer.parseInt(br.readLine());
			}
			
			while(idx != N){						// 현재 idx가 N과 같은가? 다름
				if(game[idx] != OVER){			// idx째 사람이 가리킨 사람이 OVER아 아니라면,
					int tmp = game[idx];		// 임시 변수를 통해 idx에는 현재 가리킴 당한 사람 번호, 
					game[idx] = OVER;			// game 배열에는 현재 가리키고있는 사람 번호를 서로 바꿔서 넣어줌
					idx = tmp;
					
					cnt++;							// 위의 식을 통해 한칸씩 움직이며 N이 몇번째에 있는지 알아내기 위해 cnt값을 더해줌
				}
				
				else{									// 만약 N이 존재하지 않아 game[idx] == OVER 이라면 비정상적인 종료
					cnt = 0;
					break;
				}
			}
			
			sb.append(cnt).append(NEW_LINE);
		}
		
		br.close();
		System.out.println(sb.toString());		// 결과 출력
	}
}
