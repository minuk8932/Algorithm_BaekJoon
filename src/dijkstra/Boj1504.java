package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1504번 : 특정한 최단 경로
 *
 *	@see https://www.acmicpc.net/problem/1504
 *
 */
public class Boj1504 {
	private static final String SPACE = " ";	
	private static final int INF = 1_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		ArrayList<Node>[] map = new ArrayList[N + 1];
		int[][] cost = new int[4][N + 1];
		
		for(int i = 1; i < N + 1; i++){
			cost[1][i] = INF;
			cost[2][i] = INF;
			cost[3][i] = INF;
			map[i] = new ArrayList<>();
		}
		
		for(int i = 1; i < E + 1; i++){
			st = new StringTokenizer(br.readLine(), SPACE);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			map[a].add(new Node(b, c));
			map[b].add(new Node(a, c));
		}
		
		st = new StringTokenizer(br.readLine(), SPACE);
		int must1 = Integer.parseInt(st.nextToken());				// 반드시 거쳐야하는 경로 1
		int must2 = Integer.parseInt(st.nextToken());				// 반드시 거쳐야하는 경로 2
		
		dijkstra(N, 1, 1, map, cost);											// 1 ~ N 다익스트라
		dijkstra(N, must1, 2, map, cost);									// must1 ~ N 다익스트라
		dijkstra(N, must2, 3, map, cost);									// must2 ~ N 다익스트라
		
		// must1 -> must2 -> N 의 순서와, must2 -> must1 -> N으로 탐색하는 순서 중 최솟 값 도출 : cost[2][] = must1, cost[3][] = must2
		int min = Math.min(cost[1][must1] + cost[2][must2] + cost[3][N], cost[1][must2] + cost[3][must1] + cost[2][N]);
		
		System.out.println(min >= INF ? -1 : min);
	}

	private static class Node implements Comparable<Node>{
		int dest;
		int cost;
		
		public Node(int dest, int cost){
			this.dest = dest;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node n) {										// 우선순위 큐 내에서, 큐안의 비용이 현재 들어온 다음 비용보다 작으면 앞으로, 크면 제자리
			return (this.cost < n.cost) ? -1 : 1;
		}
	}
	
	/**
	 * 
	 * 	@param N : 도착지점
	 * 	@param start : 시작노드
	 * 	@param costIdx : 몇번째에 방문하는
	 * 	@param map : 그래프
	 * 	@param cost : 경로 별 비용을 구해줄 배열
	 * 
	 */
	private static void dijkstra(int N, int start, int costIdx, ArrayList<Node>[] map, int[][] cost){
		cost[costIdx][start] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, cost[costIdx][start]));					// 시작값 offer
		
		while(!pq.isEmpty()){
			Node poll = pq.poll();
			int current = poll.dest;													// 현재 위치의 노드 번호를 받아냄
			
			if(cost[costIdx][current] < poll.cost){								// 현재 비용이 배열안의 비용보다 큰 경우, 수행할 필요 없음
				continue;
			}
			
			for(Node next : map[current]){
				if(cost[costIdx][next.dest] > cost[costIdx][current] + next.cost){		// 다음 노드의 비용 > 현재 노드의 비용 + 다음 노드로 가는 비용이면
					cost[costIdx][next.dest] = cost[costIdx][current] + next.cost;		// 다음 노드의 비용을 더작은 우변값으로 대체
						
					pq.offer(new Node(next.dest, cost[costIdx][next.dest]));			// 다음 노드에 해당하는 값과 노드 번호를 offer
				}
			}
		}
	}
}
