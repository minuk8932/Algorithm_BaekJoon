package articulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11266번: 단절점
 *
 *	@see https://www.acmicpc.net/problem/11266/
 *
 */
public class Boj11266 {
	private static int num = 0;
	private static int[] discover;
	private static boolean[] cutter;
	
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] graph = new ArrayList[V];
		discover = new int[V];
		cutter = new boolean[V];
		
		for(int i = 0; i < V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		while(E-- > 0) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()) - 1;
			int B = Integer.parseInt(st.nextToken()) - 1;
			
			graph[A].add(B);
			graph[B].add(A);
		}
		
		for(int i = 0; i < V; i++) {
			if(discover[i] != 0) continue;
			dfs(graph, i, true);
		}
		
		StringBuilder sb = new StringBuilder();
		
		int count = 0;
		LinkedList<Integer> list = new LinkedList<>();
		for(int i = 0; i < V; i++) {
			if(cutter[i]) {
				count++;
				list.add(i + 1);
			}
		}
		
		Collections.sort(list);
		
		sb.append(count).append(NEW_LINE);
		while(!list.isEmpty()) {
			sb.append(list.remove(0)).append(SPACE);
		}
		
		System.out.println(sb);
	}
	
	private static int dfs(ArrayList<Integer>[] list, int current, boolean isRoot) {
		discover[current] = ++num;			// 탐색순 넘버링
		int result = discover[current];
		int child = 0;
		
		for(int next: list[current]) {
			if(discover[next] != 0) {						// 이미 탐색된 정점? 더 앞선 순서로 초기화
				result = Math.min(result, discover[next]);
				continue;
			}
			
			child++;
			
			int prev = dfs(list, next, false);				// 뒤에 정점을 보는데,
			if(!isRoot && prev >= discover[current]) cutter[current] = true;	// 현재가 루트가 아니면서, 찾아온 정점보다 먼저 탐색된 경우, 단절점
				
			result = Math.min(prev, result);
		}
		
		if(isRoot) cutter[current] = child >= 2;	// 현 정점이 루트일 경우, 자손이 2명 이상이면 단절점.	
		return result;
	}
}
