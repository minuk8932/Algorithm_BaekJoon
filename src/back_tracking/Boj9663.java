package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 9663번: N-Queen
 *
 *	@see https://www.acmicpc.net/problem/9663/
 *
 */
public class Boj9663 {	
	private static int N = 0;
	private static int result = 0;
	private static int[] map = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for(int i = 1; i < N + 1; i++){		
			map = new int[N + 1];
			map[1] = i;			// map의 index: 행, i: 열
			
			dfs(2);				// 깊이 우선 탐색 알고리즘 실행
		}
		
		System.out.println(result);		// 최종 경우의 수 출력
	}

	/**
	 * 백트랙킹을 구현할 dfs 메소드
	 * 	@param pos: 확인해야 할 행의 위치(놓을 수 있는 자리인가?)
	 */
	private static void dfs(int pos){
		if(pos == N + 1){						// 함수가 종료될 때, 현재 N+1까지 함수가 진행되었다면, 결국 이전 N까지의 모든 행들은 조건을 만족함
			result++;							// 즉, 경우의수 +1
		}
		else{
			// pos열에 퀸을 놓을 시
			for(int i = 1; i < N + 1; i++){	// 퀸을 1에서 N번까지 놓는 모든 경우를 모두 확인해 본다. 행: 1~N, 열: pos
				map[pos] = i;					// 퀸을 i에 놓는다
				
				if(isPossible(pos)){		// 퀸이 pos에 위치할 수 있는가?
					dfs(pos + 1);			// 위치할 수 있다면, 다음 pos를 메소드에 넣고 재귀적으로 호출
				}
				else{
					map[pos] = 0;			// 위치할 수 없다면, 해당 열을 0으로 초기화: back tracking
				}
			}
		}
	}
	
	/**
	 * 
	 * @param pos 퀸을 놓을 자리
	 * @return 퀸이 그 자리에 놓일 수 있는지?
	 */
	private static boolean isPossible(int pos){		// 현재 퀸의 위치는 pos, 1~pos-1까지 검사해 pos의 퀸이 공격 당할수 있는 위치인지 확인
		for(int i = 1; i < pos; i++){
			if(map[i] == map[pos] || Math.abs(pos - i) == Math.abs(map[pos] - map[i])){	// i번째 퀸과 pos의 퀸이 같은 직선상에 있는경우
																															// i번째 퀸가 pos의 퀸이 대각선상에 놓여있는 경우(가로 세로만큼 떨어진 거리를 비교)
				return false;			// pos의 퀸이 공격 당할 수 있으므로 불가능 반환
			}
		}
		
		return true;			// 그 외의 경우 가능 반환
	}
}
