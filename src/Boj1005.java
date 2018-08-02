import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj1005 {
	private static ArrayList<Integer>[] map = null;
	private static int[] cost = null;
	
	private static boolean[] isVisited = null;
	private static long res = 0;
	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			cost = new int[N + 1];
			isVisited = new boolean[N + 1];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i < N + 1; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
			}
			
			map = new ArrayList[N + 1];
			for(int i = 1; i < N + 1; i++) {
				map[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int to = Integer.parseInt(st.nextToken());
				int from = Integer.parseInt(st.nextToken());
				
				map[from].add(to);
			}
			
			res = 0;
			
			int finish = Integer.parseInt(br.readLine());
			res += cost[finish];
			
			dfs(finish);	
			
			sb.append(res).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static void dfs(int start) {
		if(isVisited[start]) return;
		isVisited[start] = true;
		
		if(map[start] == null) return;
		
		for(int next: map[start]) {
			if(!isVisited[next]) {
				dfs(next);
			}
		}
		
		
	}
}
