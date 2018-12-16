package minimumcost_spanning_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1922번: 네트워크 연결 by kruskal
 *
 *	@see https://www.acmicpc.net/problem/1922/
 *
 */
public class Boj1922 {	
	private static int[] parent;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		ArrayList<Node> map = new ArrayList<>();
		parent = new int[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			map.add(new Node(from, to, cost));
		}
		
		System.out.println(Kruskal(map, N, M));		// 결과 출력
	}
	
	private static class Node implements Comparable<Node>{
		int start;
		int end;
		int cost;
		
		public Node(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			if(this.cost < n.cost) return -1;
			else if(this.cost > n.cost) return 1;
			else return 0;
 		}
	}
	
	private static int find(int from) {
		if(parent[from] == from) return from;
		return find(parent[from]);
	}
	
	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x < y) parent[y] = x;
		else parent[x] = y;
	}
	
	private static boolean isSame(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return true;
		else return false;
	}
	
	private static int Kruskal(ArrayList<Node> graph, int n, int m) {
		int minCost = 0;
		Collections.sort(graph);		// 비용이 작은 순으로 정렬
		
		for(Node next: graph) {
			if(!isSame(next.start, next.end)) {		// 서로 같은 부모를 갖는 경우 이미 다른 작은 값의 노드로 연결이 완료됨.
				minCost += next.cost;
					
				merge(next.start, next.end);		// 최솟 값 노드를 찾았으니, 부모노드로 연결 (합집합)
			}
		}
		
		return minCost;
	}
}
