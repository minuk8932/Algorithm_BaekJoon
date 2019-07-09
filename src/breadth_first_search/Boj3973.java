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
 *	백준 3973번: Time To Live
 *
 *	@see https://www.acmicpc.net/problem/3973/
 *
 */
public class Boj3973 {
	private static final String NEW_LINE = "\n";
	private static int[] visit;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int c = Integer.parseInt(br.readLine());
		
		while(c-- > 0) {
			int N = Integer.parseInt(br.readLine());
			
			ArrayList<Integer>[] path = new ArrayList[N];
			for(int i = 0; i < N; i++) {
				path[i] = new ArrayList<>();
			}
			
			int loop = N - 1;
			while(loop-- > 0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int node1 = Integer.parseInt(st.nextToken());
				int node2 = Integer.parseInt(st.nextToken());
				
				path[node1].add(node2);
				path[node2].add(node1);
			}
			
			int farNode = searchDeepest(N, path, 0);				// 트리의 가장 끝 노드 탐색
			int another = searchDeepest(N, path, farNode);			// 끝 노드로부터 가장 떨어진 노드 탐색(트리의 지름)
			int distance = (visit[another] - 1) / 2;
			
			sb.append((visit[another] - 1) % 2 == 0 ? distance: distance + 1).append(NEW_LINE);		// 지름의 절반을 통해 결과 도출
		}
		
		System.out.print(sb.toString());
	}
	
	private static int searchDeepest(int n, ArrayList<Integer>[] list, int start) {
		visit = new int[n];
		int node = -1, max = 0;
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		
		visit[start] = 1;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int next: list[current]) {
				if(visit[next] != 0) continue;
				visit[next] = visit[current] + 1;
				
				if(visit[next] > max) {
					max = visit[next];
					node = next;
				}
				
				q.offer(next);
			}
		}
		
		return node;
	}
}
