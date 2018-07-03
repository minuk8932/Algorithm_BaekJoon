package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13016번: 내 왼손엔 흑염룡이 잠들어 있다.
 *
 *	@see https://www.acmicpc.net/problem/13016/
 *
 */
public class Boj13016 {
	private static final String NEW_LINE = "\n";
	
	private static boolean[] isVisited = null;
	private static boolean checkIn = true;
	
	private static int[] costTmp = null;
	private static int[] cost = null;
	private static int[] far = null;

	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		cost = new int[N + 1];
		costTmp = new int[N + 1];
		far = new int[N + 1];
		isVisited = new boolean[N + 1];
		
		ArrayList<Node>[] map = new ArrayList[N + 1];
		
		for(int i = 0; i < N + 1; i++) {
			map[i] = new ArrayList<>();
		}
		
		for(int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int length = Integer.parseInt(st.nextToken());
			
			map[from].add(new Node(to, length));
			map[to].add(new Node(from, length));
		}
		
		costDfs(map, 1);			// 깊이 우선 탐색을 통한 서브 트리에 속하는, 안속하는 최장 거리를 구함
		
		isVisited = new boolean[N + 1];
		distDfs(map, 1, 0);			// 최종적으로 해당 나라마다 최장 거리를 구함
		
		for(int i = 1; i < N + 1; i++)			// 각 거리를 버퍼에 담은 후
			sb.append(far[i]).append(NEW_LINE);
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
	
	/**
	 * 깊이 우선 탐색을 통한 최장거리 구하기
	 * 
	 */
	private static void costDfs(ArrayList<Node>[] map, int current) {
	    isVisited[current] = checkIn;			// 방문 배열 초기화
	    cost[current] = costTmp[current] = 0;	
	    
	    for (Node next: map[current]) {			// 리스트 항목에 따라 하나씩 접근
	    	if (isVisited[next.node]) continue;	// 이미 방문한 것은 제외
	    	
	    	costDfs(map, next.node);		// 현재 노드에서 연결된 노드를 향해 다시 깊이 우선 탐색
	    	
	    	if (cost[current] < cost[next.node] + next.cost) {		// 끝에 도달하면 역행하며, 이전 비용 < 현재까지 비용 + 현재로 넘어오는 비용인 경우
	    		costTmp[current] = cost[current];					// 두번째로 큰 값을 저장
	    		cost[current] = cost[next.node] + next.cost;
	    	}
	    	else if (costTmp[current] < cost[next.node] + next.cost) {		// 두번째로 큰 값을 저장
	    		costTmp[current] = cost[next.node] + next.cost;
	    	}
	    }
	}
	
	/**
	 * 깊이 우선 탐색을 통한 최종거리 구하기
	 * 
	 */
	private static void distDfs(ArrayList<Node>[] map, int current, int dist) {
	    isVisited[current] = checkIn;
	    far[current] = Math.max(cost[current], dist);	// 더 큰 거리 값을 현재 인덱스에 값을 담아줌
	    
	    for (Node next: map[current]) {
	    	if (isVisited[next.node]) continue;
	    	
	    	if (cost[current] == cost[next.node] + next.cost) {		// next -> current -> cost[current]가 안되는 상황
	    		distDfs(map, next.node, Math.max(dist + next.cost, costTmp[current] + next.cost));
	    		continue;
	    	}
	    	// 그 외
	    	distDfs(map, next.node, Math.max(dist + next.cost, cost[current] + next.cost));
	    }
	}
	
	private static class Node{
		int node;
		int cost;
		
		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
	}
}
