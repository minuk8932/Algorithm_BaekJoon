package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 
 * 	@author minchoba
 *	백준 2668번: 숫자고르기
 *
 *	@see https://www.acmicpc.net/problem/2668/
 *
 */
public class Boj2668 {
	private static final String NEW_LINE = "\n";
	
	private static int N = 0;
	private static int start = 0;
	private static boolean isTrue = false;
	
	private static ArrayList<Integer>[] map = null;
	private static int[] isVisited = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new ArrayList[N + 1];
		
		for(int i = 0; i < N + 1; i++){
			map[i] = new ArrayList<>();
		}
		
		for(int i = 1; i < N + 1; i++){
			map[i].add(Integer.parseInt(br.readLine()));
		}
		
		StringBuilder sb = new StringBuilder();
		
		int cnt = 0;
		boolean[] res = new boolean[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			isVisited = new int[N + 1];
			start = i;			// 완전 탐색전 첫 시작점을 변수에 저장
			
			isTrue = false;
			
			dfs(i);			// 깊이 우선 탐색 메소드
			
			if(isTrue) {				// 사이클이 존재한 경우
				for(int j = 1; j < N + 1; j++) {
					if(res[j]) continue;		// 결과 배열 값이 참이면 변경할 필요 없음
					
					if(isVisited[j] == 1) {		// 방문 배열 값이 1인 경우 모두 참으로 변경
						res[j] = true;
					}
				}
			}	
		}
		
		for(int i = 1; i < N + 1; i++) {
			if(res[i]) {						// 참인 배열의 수를 세고, 해당 인덱스를 버퍼에 하나씩 담음
				cnt++;
				sb.append(i).append(NEW_LINE);
			}
		}
		
		System.out.println(cnt);
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
	
	/**
	 * 깊이 우선 탐색 메소드
	 * 
	 */
	private static void dfs(int current){
		if(isVisited[current] == 1) {		// 현재 방문 배열 값이 1이면 (이미 방문했던 노드)
			if(start == current) {			// 시작 인덱스와 현재 인덱스가 같은 값인가?
				isTrue = true;		// 같다면 사이클
			}
			
			return;					// 탐색 가능한 모든 노드 방문 완료 했으므로 종료
		}
		
		isVisited[current] = 1;			// 첫 방문이라면 방문 배열 값에 1 저장
		
		for(int next: map[current]) {	// 접근 가능한 다음 경로를 찾아 깊이 재귀호출
			dfs(next);
		}
	}
}
