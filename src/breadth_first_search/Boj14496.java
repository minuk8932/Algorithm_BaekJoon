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
 *	백준 14496번: 그대 그머가 되어
 *
 *	@see https://www.acmicpc.net/problem/14496/
 *
 */
public class Boj14496 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken()) - 1;
		int b = Integer.parseInt(st.nextToken()) - 1;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] path = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			path[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			path[from].add(to);
			path[to].add(from);
		}
		
		System.out.println(bfs(N, path, a, b));
	}
	
	private static int bfs(int n, ArrayList<Integer>[] list, int start, int end) {
		if(start == end) return 0;
		
		int[] isVisited = new int[n];
		int min = Integer.MAX_VALUE;
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		isVisited[start] = 1;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int next: list[current]) {
				if(isVisited[next] != 0) continue;
				isVisited[next] = isVisited[current] + 1;
				
				if(next == end) min = Math.min(min, isVisited[next]);
				q.offer(next);
			}
		}
		
		return min == Integer.MAX_VALUE ? -1: min - 1;
	}
}
