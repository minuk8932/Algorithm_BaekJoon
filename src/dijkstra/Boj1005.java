package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
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
	private static ArrayList<Node>[] map = null;
	private static int[] cost = null;
	private static int[] res = null;
	
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
			res = new int[N + 1];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i < N + 1; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
				res[i] = -1;				// 최저값 입력
			}
			
			map = new ArrayList[N + 1];
			for(int i = 1; i < N + 1; i++) {
				map[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				map[to].add(new Node(from, cost[from]));		// 역순 탐색을 위한 역순 값 입력
			}
			
			
			int finish = Integer.parseInt(br.readLine());		// 시작할 목적지 값을 입력
			
			
			dijkstra(finish);					// 다익스트라 알고리즘을 이용한 결과 배열 값을 구함
			
			int getRes = 0;
			for(int i = 1; i < N + 1; i++) {			// 결과 배열에서 최댓 값을 구하고
				if(getRes < res[i]) getRes = res[i];
			}
			
			sb.append(getRes).append(NEW_LINE);		// 값을 버퍼에 담음
		}
		
		System.out.println(sb.toString());			// 결과값 한번에 출력
	}
	
	/**
	 * 노드 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Node implements Comparable<Node>{
		int e;
		int v;
		
		public Node(int e, int v) {
			this.e = e;
			this.v = v;
		}

		@Override
		public int compareTo(Node n) {
			return this.v > n.v ? -1: 1;
		}
	}
	
	/**
	 * 다익스트라 알고리즘 메소드
	 * 
	 */
	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, cost[start]));
		
		res[start] = cost[start];
			
		while(!pq.isEmpty()) {
			Node current = pq.poll();
				
			for(Node next: map[current.e]) {
				if(res[next.e] < res[current.e] + next.v) {		// 다익스트라 알고리즘의 정의를 통해 걸리는 비용을 하나씩 결과 배열에 저장
					res[next.e] = res[current.e] + next.v;
					
					pq.offer(new Node(next.e, res[next.e]));		// 다음 값들을 우선순위 큐에 담아줌
				}
			}
		}
	}
}
