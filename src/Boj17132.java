import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17132 {
	private static ArrayList<Node>[] path;
	
	private static class Node{
		int edge;
		int cost;
		
		public Node(int edge, int cost) {
			this.edge = edge;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		path = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			path[i] = new ArrayList<>();
		}
		
		int loop = N - 1;
		while(loop-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int edge1 = Integer.parseInt(st.nextToken()) - 1;
			int edge2 = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			
			path[edge1].add(new Node(edge2, cost));
			path[edge2].add(new Node(edge1, cost));
		}
		
		System.out.println(getSum(N));
	}
	
	private static int getSum(int n) {
		boolean[] visit = new boolean[n];
		visit[0] = true;
		int sum = 0;
		
		for(Node start: path[0]) {
			visit[start.edge] = true;
			Queue<Node> q = new LinkedList<>();
			
			q.offer(start);
			
			while(!q.isEmpty()) {
				Node current = q.poll();
				
				for(Node next: path[current.edge]) {
					if(visit[next.edge]) continue;
					visit[next.edge] = true;
					
					next.cost = Math.min(next.cost, current.cost);
					sum += current.cost;
					sum += next.cost;
					
					q.offer(new Node(next.edge, next.cost));
				}
			}
		}
		
		return sum;
	}
}
