package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 3584번: 가장 가까운 공통 조상
 *
 *	@see https://www.acmicpc.net/problem/3584/
 *
 */
public class Boj3584 {
	private static int[] isVisited = null;
	private static ArrayList<Integer>[] tree = null;
	private static int parent = 0;
	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			
			isVisited = new int[N + 1];
			tree = new ArrayList[N + 1];
			
			for(int i = 0; i < N + 1; i++) {
				tree[i] = new ArrayList<>();
			}
			
			StringTokenizer st = null;
			
			for(int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				tree[to].add(from);		// 트리를 거꾸로 담아서 자손부터 역순으로 탐색
			}
			
			st = new StringTokenizer(br.readLine());
			int son1 = Integer.parseInt(st.nextToken());
			int son2 = Integer.parseInt(st.nextToken());
			
			isVisited[son1] = 1;		// 출발 노드를 정함
			dfs(son1);				// 우선 하나의 자손을 통한 깊이 우선 탐색으로 방문 배열에 숫자를 채움
			dfs(son2);				// 두번째 깊이 우선 탐색에선 또 다른 자손을 통해 탐색하는데, 방문배열이 0이 아닌경우 해당 노드를 저장하고 바로 종료
			
			sb.append(parent).append(NEW_LINE);			// 가장 가까운 공통조상을 버퍼에 담고
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
	
	/**
	 * 깊이 우선 탐색 메소드
	 * 
	 */
	private static void dfs(int start) {
		if(tree[start] == null) return;
		
		for(int next: tree[start]) {
			if(isVisited[next] == 0) {
				isVisited[next] = 1;
				
				dfs(next);
			}
			else {				// 두번째 탐색시, 첫번째 탐색때 방문했던 노드인 경우 -> 가장 가까운 공통 조상
				parent = next;
				return;
			}
		}
	}
}
