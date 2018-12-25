package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15971번: 두 로봇
 *
 *	@see https://www.acmicpc.net/problem/15971/
 *
 */
public class Boj15971 {
	private static int res = 0;
	private static boolean terminate = false;
	private static boolean[] isVisited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		ArrayList<Node>[] map = new ArrayList[N + 1];
		isVisited = new boolean[N + 1];
		
		for(int i = 0; i < N + 1; i++) {
			map[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			map[from].add(new Node(to, c));
			map[to].add(new Node(from, c));
		}
		
		dfs(map, S, E, 0, 0);
		System.out.println(res);
	}
	
	private static class Node {
		int edge;
		int cost;
		
		public Node(int edge, int cost) {
			this.edge = edge;
			this.cost = cost;
		}
	}
	
	private static void dfs(ArrayList<Node>[] arr, int current, int end, int cost, int max) {
		isVisited[current] = true;
		if(terminate) return;
		
		if(current == end) {
			terminate = true;
			res = cost - max;		// 최종 노드 도달시, 경로 중 가장 큰 값을 빼줌
			
			return;
		}
		
		for(Node next: arr[current]) {
			if(isVisited[next.edge]) continue;
			dfs(arr, next.edge, end, cost + next.cost, Math.max(max, next.cost));
		}
	}
}
