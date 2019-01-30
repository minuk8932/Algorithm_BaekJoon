import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj15586 {
	private static final String NEW_LINE = "\n";
	
	private static int[] parent;
	private static ArrayList<Node>[] arr;
	
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
		int Q = Integer.parseInt(st.nextToken());
		
		parent = new int[N];
		arr = new ArrayList[N];
		init();
		
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int usado = Integer.parseInt(st.nextToken());
			
			arr[x].add(new Node(y, usado));
			arr[y].add(new Node(x, usado));
			merge(x, y);
		}
		
		int head = 0;
		
		for(int i = 0; i < parent.length; i++) {
			if(parent[i] < 0) head = i;
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken()) - 1;
			int res = 0;
			
			if(v == head) {
				for(Node son: arr[head]) {
					if(k <= son.cost) res++;
				}
			}
			else {
				int cost = 0;
				
				for(Node son: arr[head]) {
					if(son.node == v) cost = son.cost;
				}
				
				for(Node son: arr[head]) {
					int min = Math.min(cost, son.cost);
					if(k <= min) res++;
				}
			}
			
			sb.append(res).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static void init() {
		for(int i = 0; i < parent.length; i++) {
			parent[i] = -1;
			arr[i] = new ArrayList<>();
		}
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return;
		
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
	}
}
