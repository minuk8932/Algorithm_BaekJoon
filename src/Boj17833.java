import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj17833 {
	private static ArrayList<Node>[] path;
	
	private static class Model{
		int height;
		int time;
		int exit1;
		int exit2;
		
		public Model(int height, int time, int exit1, int exit2) {
			this.height = height;
			this.time = time;
			this.exit1 = exit1;
			this.exit2 = exit2;
		}
	}
	
	private static class Node implements Comparable<Node>{
		int node;
		int cost;
		
		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			return this.cost < n.cost ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		int M = Integer.parseInt(br.readLine());
		Model[] campus = new Model[M];
		
		for(int i = 0; i < M ;i++) {
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			int E1 = Integer.parseInt(st.nextToken());
			int E2 = Integer.parseInt(st.nextToken());
			
			campus[i] = new Model(H, T, E1, E2);
		}
		
		makePath(N, campus);
		System.out.println(construction(N, R, D, M, campus));
	}
	
	private static void makePath(int n, Model[] arr) {
		path = new ArrayList[n + 1];
		for(int i = 0; i < n + 1; i++) {
			path[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = i + 1; j < arr.length; j++) {
				Model a = arr[i];
				Model b = arr[j];
				
				if(a.exit2 + b.height <= n) {
					
				}
				
				if(a.exit1 + b.height <= n) {
					
				}
				
				if(b.exit2 + a.height <= n) {
					
				}
				
				if(b.exit1 + a.height <= n) {
					
				}
			}
		}
	}
	
	private static int construction(int n, int r, int d, int m, Model[] arr) {
		
		
		return 0;
	}
}
