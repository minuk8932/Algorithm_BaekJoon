package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15971번: 두 로봇
 *
 *	@see https://www.acmicpc.net/problem/15971/
 *
 */
public class Boj15971 {
	private static final int INF = 100_001;

	private static ArrayList<Node>[] map = null;
	private static int[] res = new int[INF];
	
	private static boolean isTerminate = false;
	private static boolean[] isVisited = new boolean[INF];
	private static boolean[] isPassed = new boolean[INF];
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N + 1];
		
		for(int i = 0; i < N + 1; i++) {
			map[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			map[from].add(new Node(to, c));
			map[to].add(new Node(from, c));
		}
		
		bfs(N, S, E);	// 너비 우선 탐색 메소드를 통한 유망한 노드 검출
		dfsRev(S, E);	// 깊이 우선 탐색 메소드를 통해 유망한 노드 중 정답에 해당하는 노드 검출
		
		long sum = 0, max = 0;
				
		for(int i = 1; i < N + 1; i++) {
			sum += res[i];					// 총합을 구함
			
			if(res[i] > max) max = res[i];	// 총합 중 최댓 값을 뽑아냄
		}
		
		System.out.println(sum - max);		// 이동 거리 중 최소 거리를 결과로 출력
	}
	
	/**
	 * 노드 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Node{
		int edge;
		int cost;
		
		public Node(int edge, int cost) {
			this.edge = edge;
			this.cost = cost;
		}
	}
	
	/**
	 * 너비 우선 탐색 메소드
	 * 
	 */
	private static void bfs(int N, int start, int end) {		
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		isVisited[start] = true;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(Node next: map[current]) {
				if(!isVisited[next.edge]) {
					isVisited[next.edge] = true;
					
					if(next.edge == end) return;		// 끝 점에 도달 시 반복문 종료
					
					q.offer(next.edge);
				}
			}
		}
	}
	
	/**
	 * 깊이 우선 탐색 메소드
	 * 
	 */
	private static void dfsRev(int start, int end) {
		if(start == end) isTerminate = true;		// 시작과 종료 노드가 같은 경우 종료 변수를 참으로 변경
		
		if(isVisited[start] && !isPassed[start]) {
			isPassed[start] = true;
			
			for(Node next: map[start]) {
				if(isVisited[next.edge] && !isPassed[next.edge]) {
					res[next.edge] = next.cost;
					
					dfsRev(next.edge, end);			// 깊이 우선 탐색 메소드 재귀 호출
					if(isTerminate) return;			// 종료 변수가 참인 경우 메소드 종료
				}
			}
			
			dfsRev(start, end);						// 역으로 돌아가서 깊이 우선 탐색 실행
			if(start != end) res[start] = 0;		// 종료 노드가 도착점이 아닌 경우
		}
	}
}
