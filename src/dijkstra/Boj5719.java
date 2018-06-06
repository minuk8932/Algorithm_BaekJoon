package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5719번: 거의최단경로
 *
 *	@see https://www.acmicpc.net/problem/5719/
 *
 */
public class Boj5719 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final int INF = 10_001;
	
	private static ArrayList<Node>[] map = null;
	private static ArrayList<Node>[] revMap = null;
	private static boolean[][] isVisited = null;
	private static int[] cost = null;
	private static int N = 0;
	private static int M = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			if(N == 0 && M == 0) {
				break;
			}
			
			st = new StringTokenizer(br.readLine(), SPACE);
			int S = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			
			map = new ArrayList[N];
			revMap = new ArrayList[N];
			cost = new int[N];
			isVisited = new boolean[N][N];
			
			for(int i = 0; i < N; i++){			// 각 배열 초기화
				map[i] = new ArrayList<>();
				revMap[i] = new ArrayList<>();
				cost[i] = INF;
			}
			
			for(int i = 0; i < M; i++){
				st = new StringTokenizer(br.readLine(), SPACE);
				int U = Integer.parseInt(st.nextToken());
				int V = Integer.parseInt(st.nextToken());
				int P = Integer.parseInt(st.nextToken());
				
				map[U].add(new Node(V, P));			// 본래 경로 저장
				revMap[V].add(new Node(U, P));		// 역경로 저장, 최단경로 간선 제거를 위한
			}
			
			dijkstra(S, D);			// 다익스트라 메소드를 통해 최단경로를 구함
			bfs(S, D);				// 너비우선탐색 메소드를 통해 최단경로의 간선을 제거
			
			Arrays.fill(cost, INF);			// 비용 배열을 다시 최댓값으로 초기화 한 후
			int res = almostDijkstra(S, D);	// 거의 최단경로를 다익스트라 알고리즘을 통해 구하고
			
			sb.append(res == INF ? -1 : res).append(NEW_LINE);	// 반환된 결과가 INF 값이면 -1 아니면 해당 거의 최단 경로 값을 버퍼에 저장
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
	
	/**
	 * 간선, 비용 이너 클래스 
	 * @author minchoba
	 *
	 */
	private static class Node implements Comparable<Node>{
		int dest;
		int cost;
		
		public Node(int dest, int cost){
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost < n.cost ? -1 : 1;
		}		
	}
	
	/**
	 * 최단 경로 알고리즘
	 * @param S: 출발지
	 * @param D: 목적지
	 */
	private static void dijkstra(int S, int D){		
		cost[S] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(S, cost[S]));
		
		while(!pq.isEmpty()){
			Node current = pq.poll();
			
			for(Node next : map[current.dest]){			// 인접리스트에 현재 방문한 위치를 담아서
				if(cost[next.dest] > cost[current.dest] + next.cost){	// 다음 경로에 대한 정보를 가져온 후 비용을 비교해서
					cost[next.dest] = cost[current.dest] + next.cost;	// 다음 경로의 비용이 더 크면 현재 + 다음까지의 비용을 담아줌
					
					pq.offer(new Node(next.dest, cost[next.dest]));
				}
			}
		}
	}
	
	/**
	 * 너비우선탐색 알고리즘
	 * @param S: 출발지
	 * @param D: 목적지
	 */
	private static void bfs(int S, int D) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(D);				// 목적지로부터 역추적
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			if(current != S) {		// 현재 위치가 시작점이 아닐때 까지
				for(Node next : revMap[current]) {		// 역추적해가면서
					if(cost[current] == cost[next.dest] + next.cost) {	// 현재 역으로 진행한 위치의 비용 == 다음위치의 비용 + 다음거리 비용 이면
						isVisited[next.dest][current] = true;	// 그때의 배열값에 방문 배열을 참으로 변경
						q.offer(next.dest);
					}
				}
			}
		}
	}
	
	/**
	 * 거의 최단 경로 알고리즘
	 * @param S: 출발
	 * @param D: 도착
	 * @return: 거의 최단 경로까지의 최소 비용
	 */
	private static int almostDijkstra(int S, int D){
		int min = INF;
		
		cost[S] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(S, cost[S]));
		
		while(!pq.isEmpty()){
			Node current = pq.poll();
			
			for(Node next : map[current.dest]){		// 일반적인 다익스트라와 같이 진행하지만, 방문하려는 위치의 방문배열 값이 거짓인 것만 골라서 확인
				if(!isVisited[current.dest][next.dest] && cost[next.dest] > cost[current.dest] + next.cost ){	// 즉 이전에 구한 최단경로는 제외
					cost[next.dest] = cost[current.dest] + next.cost;
					
					if(next.dest == D) {						// 목적지에 도착한 경우 최소값을 min 변수에 담아줌
						min = Math.min(min, cost[next.dest]);
					}
					
					pq.offer(new Node(next.dest, cost[next.dest]));
				}
			}
		}
		
		return min;		// 최단거리 비용 반환
	}
}
