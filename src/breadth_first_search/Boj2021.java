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
 *	백준 2021번: 최소 환승 경로
 *
 *	@see https://www.acmicpc.net/problem/2021/
 *
 */
public class Boj2021 {	
	
	private static class Node{
		int edge;
		int cost;
		
		public Node(int edge, int cost) {
			this.edge = edge;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] lane = new ArrayList[N + L + 1];
		for(int i = 1; i < N + L + 1; i++) {
			lane[i] = new ArrayList<>();
		}
		
		for(int center = N + 1; center < L + N + 1; center++) {		// 가상 역 추가
			st = new StringTokenizer(br.readLine());
			
			while(st.hasMoreTokens()) {
				int node = Integer.parseInt(st.nextToken());
				if(node == -1) break;
				
				lane[center].add(node);
				lane[node].add(center);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs(N, L, lane, start, end));
	}
	
	private static int bfs(int n, int l, ArrayList<Integer>[] list, int s, int e) {
		boolean[] isVisited = new boolean[n + l + 1];
		Queue<Node> q = new LinkedList<>();
		
		for(int virtual: list[s]) {
			isVisited[virtual] = true;
			
			for(int real: list[virtual]) {			// 시작 역에 연결된 가상역과 같이 묶여있는 -> 환승이 필요없는 역을 찾음
				if(isVisited[real]) continue;
				isVisited[real] = true;

				q.offer(new Node(real, 0));
			}
		}
		
		while(!q.isEmpty()) {
			Node current = q.poll();
			if(current.edge == e) return current.cost;
			
			for(int virtual: list[current.edge]) {
				if(isVisited[virtual]) continue;
				isVisited[virtual] = true;

				for(int next: list[virtual]) {
					if(isVisited[next]) continue;
					isVisited[next] = true;
					
					q.offer(new Node(next, current.cost + 1));		// 환승 +1
				}
			}
		}

		return -1;		// 도착 못한 경우
	}
}
