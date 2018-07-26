import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2908 {
	private static int[] cost = null;
	private static int[][] dp = null;
	
	private static final int INF = 1_000_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Node>[] map = new ArrayList[N];
		for(int i = 0; i < N; i++){
			map[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int cost = Integer.parseInt(st.nextToken());
				
				if(cost == 0) continue;
				map[i].add(new Node(j, cost));
			}
		}		
		
		cost = new int[N];
		dp = new int[N][N];
		
		bfs(N, map);
		
		
	}
	
	private static class Node{
		int node;
		int cost;
		
		public Node (int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
	}
	
	private static void bfs(int N, ArrayList<Node>[] map) {
		
	}
}
