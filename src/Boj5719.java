import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj5719 {
	private static final String SPACE = " ";
	private static final int INF = 1_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int N= Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			if(N == 0 && M == 0) {
				break;
			}
			
			st = new StringTokenizer(br.readLine(), SPACE);
			int S = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			
			ArrayList<Node>[] map = new ArrayList[N];
			int[] cost = new int[N];
			
			for(int i = 0; i < N; i++){
				map[i] = new ArrayList<>();
				cost[i] = INF;
			}
			
			for(int i = 0; i < M; i++){
				st = new StringTokenizer(br.readLine(), SPACE);
				map[Integer.parseInt(st.nextToken())].add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			dijkstra(map, cost, S, D, N, M);
		}
	}
	
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
	
	private static void dijkstra(ArrayList<Node>[] map, int[] cost, int S, int D, int N, int M){
		int min = Integer.MAX_VALUE;
		
		cost[S] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(S, cost[S]));
		
		while(!pq.isEmpty()){
			Node poll = pq.poll();
			int current = poll.dest;
			
			for(Node next : map[current]){
				if(cost[next.dest] > cost[current] + next.cost){
					cost[next.dest] = cost[current] + next.cost;
					
					pq.offer(new Node(next.dest, cost[next.dest]));
				}
			}
			
			if(cost[current] != INF && current == D){
				min = Math.min(min, cost[current]);
			}
		}
		System.out.println(min);		
	}
}
