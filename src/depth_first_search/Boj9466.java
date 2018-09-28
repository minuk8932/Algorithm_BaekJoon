package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9466번: 텀 프로젝트
 *
 *	@see https://www.acmicpc.net/problem/9466/
 *
 */
public class Boj9466 {
	private static final String NEW_LINE = "\n";
	
	private static int[] term = null;
	private static boolean[] isVisited = null;
	private static int[] res = null;
	private static int cycle = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			int n = Integer.parseInt(br.readLine());
			
			isVisited = new boolean[n + 1];		// 방문했는지
			term = new int[n + 1];				// 팀 정보
			res = new int[n + 1];				// 사이클 체커
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1; i < n + 1; i++) term[i] = Integer.parseInt(st.nextToken());
			
			cycle = 0;
			
			for(int i = 1; i < n + 1; i++) {
				if(isVisited[i]) continue;
				
				dfs(i);		// 깊이 우선 탐색 메소드
			}
			
			sb.append(n - cycle).append(NEW_LINE);		// 팀이 짜여진 인원을 제외한 나머지 인원를 버퍼에 저장
		}
		
		System.out.println(sb.toString());			// 결과 값 한번에 출력
	}
	
	/**
	 * 깊이 우선 탐색 메소드
	 * 
	 */
	private static void dfs(int current) {
		if(res[current] != 0) return;		// 이미 탐색한 인원이면 재귀 종료
		isVisited[current] = true;		// 방문 하지 않은경우
		
		int next = term[current];		// 다음 깊이를 탐색하기 위한 해당 배열의 값을 저장
		
		if(!isVisited[next]) {		// 다음 깊이가 탐색이 되지 않은 경우
			dfs(next);				// 다시 재귀 호출
		}
		else {
			if(res[next] == 0) {	// 탐색되지 않은 인원의 경우
				cycle++;
				
				for(int i = next; i != current; i = term[i]) cycle++;		// 사이클에 걸리는 인원의 수를 증가
			}
			
			// 이미 탐색이 된 인원의 경우 -> 이전 탐색에서 싸이클에 포함되지 않았거나, 이미 포함된 경우 이므로 배제
		}
		
		res[current] = 1;		// 탐색 완료
		return;
	}
}
