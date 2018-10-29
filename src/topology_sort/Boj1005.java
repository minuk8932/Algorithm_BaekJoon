package topology_sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1005번: ACM Craft
 *
 *	@see https://www.acmicpc.net/problem/1005/
 *
 */
public class Boj1005 {
	private static ArrayList<Integer>[] map;
	private static int[] cost;
	private static int[] indegree;
	private static int[] time;
	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			cost = new int[N + 1];
			indegree = new int[N + 1];
			time = new int[N + 1];
			
			for(int i = 0; i < N + 1; i++) {
				time[i] = -1;
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i < N + 1; i++) {
				time[i] = Integer.parseInt(st.nextToken());		// 소요 시간
			}
			
			map = new ArrayList[N + 1];
			for(int i = 0; i < N + 1; i++) {
				map[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				map[from].add(to);
				indegree[to]++;			 // 상위 노드의 갯수 저장: 위상
			}
			
			
			int finish = Integer.parseInt(br.readLine());			
			bfs(N);
			
			sb.append(cost[finish]).append(NEW_LINE);			// 지어야하는 건물에대한 최장 거리를 버퍼에 저장
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
	
	/**
	 * 너비 우선 탐색 알고리즘
	 * 
	 */
	private static void bfs(int n) {
		Queue<Integer> q = new LinkedList<>();
		
		for(int i = 1; i < n + 1; i++) {	// 위상이 0인 노드의 번호부터 큐에 저장
			if(indegree[i] == 0) {
				q.offer(i);
				cost[i] = time[i];			// 그때의 비용은 건물을 짓는 시간과 동일
			}
		}
			
		for(int i = 1; i < n + 1; i++) {
			while(!q.isEmpty()) {
				int current = q.poll();
				
				for(int next: map[current]) {
					indegree[next]--;			// 현 노드의 다음노드의 위상 갯수를 1개씩 줄여줌
					
					if(cost[next] < cost[current] + time[next]) {	// 다음 노드의 비용을 가장 큰 값으로 갱신
						cost[next] = cost[current] + time[next];
					}
					
					if(indegree[next] == 0) q.offer(next);		// 다음 노드의 위상이 종료되면 해당 노드의 번호를 큐에 저장
				}
			}
		}
	}
}