package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10282번 : 해킹
 *
 *	@see https://www.acmicpc.net/problem/10282
 *
 */
public class Boj10282 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	private static final int INF = 10_000_001;
	
	private static int[] cost = null;
	private static ArrayList<Node>[] com = null;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			com = new ArrayList[n + 1];
			cost = new int[n + 1];
			
			boolean[] isVisited = new boolean[n + 1];
			int coms = 0;

			for (int i = 0; i < n + 1; i++) {
				cost[i] = INF;
				com[i] = new ArrayList<>();
			}

			for (int i = 1; i < d + 1; i++) {
				st = new StringTokenizer(br.readLine(), SPACE);
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());

				com[b].add(new Node(a, s));
			}
			
			cost[c] = 0;
			isVisited[c] = true;
			
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.offer(new Node(c, cost[c]));

			while (!pq.isEmpty()) {
				Node poll = pq.poll();
				int current = poll.dest;
				
				if(poll.cost > cost[current]){ 
					continue;
				}

				for (Node next : com[current]) {					
					if(cost[next.dest] > cost[current] + next.cost){				// 다음 목적지 값 > 현재목적지 값 + 다음 목적지까지의 걸리는 시간
						cost[next.dest] = cost[current] + next.cost;				// 더 작은 값으로 최소화
						
						pq.offer(new Node(next.dest, cost[next.dest]));
					}
				}
			}
			
			int time = 0;
			
			for(int i = 1; i < n + 1; i++){														// 비용이 INF가 아닐 경우
				if(cost[i] < INF){
					coms++;																			// 컴퓨터 갯수 추가
					time = Math.max(time, cost[i]);										// 모든 컴퓨터가 해킹까지 걸린 최대 시간 저장
				}
			}

			sb.append(coms).append(SPACE).append(time).append(NEW_LINE);	// 버퍼에 결과를 담고
		}

		System.out.println(sb.toString());												// 결과 한번에 출력
	}
	
	/**
	 * 
	 * 	@author minchoba
	 *
	 */
	private static class Node implements Comparable<Node> {
		int dest;
		int cost;
		
		/**
		 * 
		 * @param dest : 다음 목적지
		 * @param cost : 걸리는 시간
		 * 	
		 */
		public Node(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return n.cost > this.cost ? -1 : 1;				// 방금 들어온 비용이 큐 내에 있던 비용보다 작다면 해당 노드를 앞으로 땡겨줌
		}
	}
}
