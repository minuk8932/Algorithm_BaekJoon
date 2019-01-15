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
 *	백준 3075번: Astromeeting
 *
 *	@see https://www.acmicpc.net/problem/3075/
 *
 */
public class Boj3075 {
	private static long[][] distance;
	
	private static final char SPACE = ' ';
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			
			int[] person = new int[n];
			distance = new long[p + 1][n];
			
			for(int i = 0; i < n; i++) {
				person[i] = Integer.parseInt(br.readLine());
			}
			
			ArrayList<Node>[] galaxy = new ArrayList[p + 1];
			long cost[][] = new long[p + 1][p + 1];
			
			for(int i = 0; i < p + 1; i++) {
				galaxy[i] = new ArrayList<>();
				Arrays.fill(distance[i], Long.MAX_VALUE);
				Arrays.fill(cost[i], Long.MAX_VALUE);
			}
			
			while(q-- > 0) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int dist = Integer.parseInt(st.nextToken());
				
				if(dist < cost[from][to]) cost[from][to] = dist;		// 중복으로 들어오는 거리 조정
				galaxy[from].add(new Node(to, cost[from][to]));
				galaxy[to].add(new Node(from, cost[from][to]));
			}
			
			dijkstra(n, p, person, galaxy);
			
			Node res = getResult(p, n);
			sb.append(res.to).append(SPACE).append(res.cost).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static class Node implements Comparable<Node>{
		int to;
		long cost;
		
		public Node(int to, long cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost < n.cost ? -1 : 1;
		}
	}
	
	private static void dijkstra(int N, int P, int[] pArr, ArrayList<Node>[] map) {		
		for(int i = 0; i < pArr.length; i++) {
			distance[pArr[i]][i] = 0;			// 본인 위치는 0으로 초기화
			
			for(Node start: map[pArr[i]]) {
				PriorityQueue<Node> pq = new PriorityQueue<>();
				pq.offer(new Node(start.to, start.cost));

				distance[start.to][i] = start.cost;
				
				while(!pq.isEmpty()) {
					Node current = pq.poll();
					
					for(Node next: map[current.to]) {
						if(distance[next.to][i] > distance[current.to][i] + next.cost) {
							distance[next.to][i] = distance[current.to][i] + next.cost;
							
							pq.offer(new Node(next.to, distance[next.to][i]));
						}
					}
				}
			}
		}
	}
	
	private static Node getResult(int P, int N) {
		int planet = 0;
		long min = Long.MAX_VALUE;

		for(int i = 1; i < P + 1; i++) {
			long cost = 0;
			boolean isBreak = false;
			
			for(int j = 0; j < N; j++) {
				if(distance[i][j] == Long.MAX_VALUE) {		// 이 행성에 도달 못한 사람이 있는 경우
					isBreak = true;
					break;
				}

				cost += (distance[i][j] * distance[i][j]);
			}
			
			if(!isBreak && cost < min) {		// 도달 못한 사람이 존재하면 그곳은 모임 장소로 쓰일 수 없다.
				min = cost;
				planet = i;
			}
		}
		
		return new Node(planet, min);
	}
}
