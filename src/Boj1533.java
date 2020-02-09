import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj1533 {
	private static final int MOD = 100_0003;
	private static ArrayList<Node>[] path;
	
	private static class Node{
		int node;
		int cost;
		
		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken()) - 1;
		int E = Integer.parseInt(st.nextToken()) - 1;
		int T = Integer.parseInt(st.nextToken());
		
		path = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			path[i] = new ArrayList<>();
		}
		
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < N ;j++) {
				map[i][j] = line.charAt(j) - '0';
				if(map[i][j] != 0) path[i].add(new Node(j, map[i][j]));
			}
		}
		
		System.out.println(findPath(N, S, E, T, map));
	}
	
	private static int findPath(int n, int s, int e, int t, int[][] map) {
		
		
		return 0;
	}
}
