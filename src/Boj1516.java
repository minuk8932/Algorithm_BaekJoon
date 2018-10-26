import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj1516 {
	private static ArrayList<Node>[] map = null;
	private static int[] res = null;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		res = new int[N + 1];
		map = new ArrayList[N + 1];
		for(int i = 0; i < N + 1; i++) {
			map[i] = new ArrayList<>();
		}
		
		for(int from = 1; from < N + 1; from++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			
			while(st.hasMoreTokens()) {
				int to = Integer.parseInt(st.nextToken());
				map[from].add(new Node(to == -1 ? 0 : to, cost));
			}
		}
		
		topologySort(N);
	}
	
	private static class Node{
		int edge;
		int cost;
		
		public Node(int edge, int cost) {
			this.edge = edge;
			this.cost = cost;
		}
	}
	
	private static void topologySort(int n) {
		
	}
}
