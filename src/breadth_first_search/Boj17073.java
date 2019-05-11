package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17073번: 나무 위의 물방울
 *
 *	@see https://www.acmicpc.net/problem/17073/
 *
 */
public class Boj17073 {
	private static ArrayList<Integer>[] tree;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		double W = Double.parseDouble(st.nextToken());
		
		tree = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		int loop = N - 1;
		
		while(loop-- > 0) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken()) - 1;
			int node2 = Integer.parseInt(st.nextToken()) - 1;
			
			tree[node1].add(node2);
			tree[node2].add(node1);
		}
		
		int terminal = bfs(N);
		System.out.println(W / terminal);
	}
	
	private static int bfs(int n) {
		boolean[] visit = new boolean[n];
		boolean[] last = new boolean[n];
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(0);
		
		visit[0] = true;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			int node = -1;
			
			for(int next: tree[current]) {
				if(visit[next]) continue;
				node = next;
				
				visit[next] = true;
				q.offer(next);
			}
			
			if(node == -1) last[current] = true;			// 터미널 노드 체크
		}
		
		int result = 0;
		for(int i = 0; i < n; i++) {
			if(last[i]) result++;
		}
		
		return result;
	}
}
