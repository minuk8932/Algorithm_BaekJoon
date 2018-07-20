import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1707 {
	private static final String NEW_LINE = "\n";
	
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int INF = 200_001;
	
	private static int[] isVisited = null;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int K = Integer.parseInt(br.readLine());
		
		while(K-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			boolean[] isIn = new boolean[INF];
			ArrayList<Integer>[] graph = new ArrayList[V + 1];
			for(int i = 0; i < V + 1; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				graph[from].add(to);
				graph[to].add(from);
				isIn[from] = isIn[to] = true;
			}
			
			isVisited = new int[V + 1];
			
			bfs(V, E, graph, isIn);
			sb.append(res(V, E, graph) ? "YES" : "NO").append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static void bfs(int N, int M, ArrayList<Integer>[] map, boolean[] isIn) {		
		for(int i = 1; i < N + 1; i++) {
			if(!isIn[i]) continue;
			if(isVisited[i] != 0) continue;
			
			isVisited[i] = ONE;
			
			Queue<Integer> q = new LinkedList<>();
			q.offer(i);
			
			while(!q.isEmpty()) {
				int current = q.poll();
				
				for(int next: map[current]) {
					if(isVisited[next] == 0) {
						if(isVisited[current] == ONE) isVisited[next] = TWO;
						else if(isVisited[current] == TWO) isVisited[next] = ONE;

						q.offer(next);
					}
				}
			}
		}
	}
	
	private static boolean res(int N, int M, ArrayList<Integer>[] map) {		
		for(int i = 1; i < N + 1; i++) {
			if(isVisited[i] == 0) continue;
			
			Queue<Integer> q = new LinkedList<>();
			q.offer(i);
			
			while(!q.isEmpty()) {
				int current = q.poll();
				
				for(int next: map[current]) {
					if(isVisited[current] == isVisited[next]) return false;
				
					q.offer(next);
				}
			}
		}
		
		return true;
	}
}
