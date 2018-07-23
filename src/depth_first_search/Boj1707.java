package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1707번: 이분 그래프
 *
 *	@see https://www.acmicpc.net/problem/1707/
 *
 */
public class Boj1707 {
	private static final String NEW_LINE = "\n";
	private static final int INF = 200_001;
	
	private static ArrayList<Integer>[] graph = null;
	private static int[] isVisited = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int K = Integer.parseInt(br.readLine());
		
		while(K-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			boolean[] isIn = new boolean[INF];
			graph = new ArrayList[V + 1];
			for(int i = 0; i < V + 1; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				graph[from].add(to);
				graph[to].add(from);
				isIn[from] = isIn[to] = true;
			}
			
			isVisited = new int[V + 1];
			boolean res = false;
			
			for(int start = 1; start < V + 1; start++) {
				if(!isIn[start]) continue;	// 등장하지 않는 노드는 패스
				
				dfs(start);		// 깊이 우선 탐색 알고리즘 시작, 이를 통해 이분 그래프의 모양을 만든 후
			}
			
			for(int start = 1; start < V + 1; start++) {
				if(!isIn[start]) continue;
				
				res = search(start);		// 만들어진 이분 그래프를 깊이 우선탐색으로 다시 검사함
				if(!res) break;			// 만약 메소드의 결과가 거짓이었다면 바로 종료
			}
			
			sb.append(res ? "YES" : "NO").append(NEW_LINE);	// res의 값에 따른 결과를 버퍼에 담고
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
	
	/**
	 * 깊이 우선 탐색 메소드
	 * 
	 */
	private static void dfs(int current) {
		if(isVisited[current] == 0) isVisited[current] = 1;	// 만약 가장 처음 노드라면 1을 할당
		
		for(int next: graph[current]) {				// 다음으로 연결되는 노드를 찾음
			if(isVisited[next] == 0) {
				isVisited[next] = isVisited[current] + 1;	// 그 노드가 0이라면 현재 노드 + 1 값을 대입
				
				dfs(next);		// 다음 깊이를 탐색
			}
		}
	}
	
	/**
	 * 깊이 우선 탐색을 통한 이분 그래프 정당성 확인
	 * 
	 */
	private static boolean search(int current) {
		for(int next: graph[current]) {					// 방문 배열의 현재와 다음을 검사하여
			if(isVisited[current] % 2 == isVisited[next] % 2) return false;		// 둘 다 짝수 또는 둘 다 홀수라면 거짓을 반환
		}
		
		return true;		// 위의 경우가 없다면 참 반환
	}
}
