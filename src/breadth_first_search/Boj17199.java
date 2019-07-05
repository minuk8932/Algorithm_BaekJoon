package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17199번: Milk Factory
 *
 *	@see https://www.acmicpc.net/problem/17199/
 *
 */
public class Boj17199 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer>[] path1 = new ArrayList[N];
		ArrayList<Integer>[] path2 = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			path1[i] = new ArrayList<>();
			path2[i] = new ArrayList<>();
		}
		
		int loop = N - 1;
		while(loop-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken()) - 1;
			int node2 = Integer.parseInt(st.nextToken()) - 1;
			
			path1[node1].add(node2);			// 단방향 1, 2
			path2[node2].add(node1);
		}
		
		int result1 = getNode(N, path1);
		int result2 = getNode(N, path2);
		
		int min = Math.min(result1, result2);
		
		System.out.println(min == -1 ? Math.max(result1, result2): min);		// 둘다 -1이 아니면 min 출력
	}
	
	private static int getNode(int n, ArrayList<Integer>[] list) {
		for(int start = 0; start < n; start++) {
			boolean[] visit = new boolean[n];
			
			Queue<Integer> q = new LinkedList<>();
			q.offer(start);
			
			visit[start] = true;
			
			while(!q.isEmpty()) {
				int current = q.poll();
				
				for(int next: list[current]) {
					if(visit[next]) continue;
					visit[next] = true;
					
					q.offer(next);
				}
			}
			
			if(rotating(visit)) return start + 1;		// 탐색 가능한 경우
		}
		
		return -1;
	}
	
	private static boolean rotating(boolean[] v) {
		for(int i = 0; i < v.length; i++) {
			if(!v[i]) return false;
		}
		
		return true;
	}
}
