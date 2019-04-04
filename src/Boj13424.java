import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj13424 {
	private static final String NEW_LINE = "\n";
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
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			path = new ArrayList[N];
			for(int i = 0; i < N; i++) {
				path[i] = new ArrayList<>();
			}
			
			while(M-- > 0) {
				st = new StringTokenizer(br.readLine());
				int e1 = Integer.parseInt(st.nextToken());
				int e2 = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				path[e1].add(new Node(e2, c));
				path[e2].add(new Node(e1, c));
			}
			
			int K = Integer.parseInt(br.readLine());
			ArrayList<Integer> friend = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			while(K-- > 0) {
				friend.add(Integer.parseInt(st.nextToken()));
			}
			
			sb.append(bfs(N, friend)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int bfs(int n, ArrayList<Integer> start) {
		
		
		return 0;
	}
}
