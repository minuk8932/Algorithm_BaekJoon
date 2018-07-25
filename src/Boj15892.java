import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj15892 {
	private static int robots = 0;
	private static ArrayList<Node>[] map = null;

	private static int n = 0;
	private static boolean isVisited = false;
	private static boolean isTerminate = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		map = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++) map[i] = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			map[from].add(new Node(to, cost));
		}

		while(true) {
			isVisited = false;
			isTerminate = false;
			dfs(1);
			
			if(!isVisited) break;
		}
		
		System.out.println(robots);
	}

	private static class Node {
		int edge;
		int cost;

		public Node(int edge, int cost) {
			this.edge = edge;
			this.cost = cost;
		}
	}

	private static void dfs (int start) {		
		for (Node next : map[start]) {
			
			if (next.cost > 0) {
				next.cost--;
				isVisited = true;
				
				System.out.print(start + " " + next.edge + " ");
				
				if (next.edge == n) {
					robots++;
					isTerminate = true;
					System.out.println();
					return;
				}
				
				dfs(next.edge);
				if(isTerminate) {
					System.out.println();
					return;
				}
			}
		}
	}
}
