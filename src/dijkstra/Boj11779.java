package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11779번: 최소비용 구하기 2
 *
 *	@see https://www.acmicpc.net/problem/11779/
 *
 */
public class Boj11779 {
	private static ArrayList<Node>[] map = null;
	private static StringTokenizer st = null;

	private static long[] cost = null;
	private static int[] trace = null;

	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		map = new ArrayList[n + 1];
		trace = new int[n + 1];
		cost = new long[n + 1];
		
		for (int i = 0; i < n + 1; i++) {
			map[i] = new ArrayList<>();
			trace[i] = -1;
			cost[i] = Long.MAX_VALUE;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			map[from].add(new Node(to, cost));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dijkstra(start, end);		// 다익스트라 알고리즘 메소드
		
		StringBuilder sb = new StringBuilder();
		sb.append(cost[end]).append(NEW_LINE);		// 목적지까지의 최소비용을 버퍼에 저장
		
		LinkedList<Integer> path = new LinkedList<>();
		path.add(end);
		while(trace[end] != start) {		// 시작점 전까지 도달가능한 노드의 번호를 연결리스트에 저장
			end = trace[end];
			path.add(end);
		}
		path.add(start);		// 시작점도 저장
		
		sb.append(path.size()).append(NEW_LINE);		// 경로의 갯수를 버퍼에 저장

		while(!path.isEmpty()) {
			sb.append(path.removeLast()).append(SPACE);		// 연결리스트 역순으로 뽑아내어 버퍼에 경로를 저장
		}
		
		System.out.print(sb.toString());				// 결과 값 한번에 출력
	}

	private static class Node implements Comparable<Node> {
		int node;
		long val;

		public Node(int node, long val) {
			this.node = node;
			this.val = val;
		}

		@Override
		public int compareTo(Node n) {
			if(this.val < n.val) return -1;
			else if(this.val > n.val) return 1;
			else return 0;
		}
	}

	private static void dijkstra(int s, int e) {
		cost[s] = 0;
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(s);

		while (!pq.isEmpty()) {
			int current = pq.poll();

			for (Node next : map[current]) {
				long nextCost = next.val + cost[current];
				
				if (cost[next.node] > nextCost) {
					cost[next.node] = nextCost;
					trace[next.node] = current;
					
					pq.offer(next.node);
				}
			}
		}
	}
}
