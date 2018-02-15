package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6118 : 숨바꼭질
 *
 *	@see https://www.acmicpc.net/problem/6118
 *
 */
public class Boj6118 {
	private static final String SPACE = " ";
	
	private static final int INF = 100_001;
	private static final int START = 1;
	
	public static void main(String[] args) throws Exception{
		//버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Node>[] barn = new ArrayList[N + 1];		// 최대 N : 20000, 따라서 인접리스트로 구현
		
		int[] cost = new int[N + 1];										// 해당 노드에서 비용
		
		for(int i = 0; i < N + 1; i++){										// 리스트 및 배열 초기화
			cost[i] = INF;
			barn[i] = new ArrayList<>();
		}
		
		for(int i = 1; i < M + 1; i++){
			st = new StringTokenizer(br.readLine(), SPACE);
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			barn[from].add(new Node(to, 1));						// 양방향 그래프
			barn[to].add(new Node(from, 1));
		}
		br.close();
		
		cost[START] = 0;														// 시작 값 초기화
		
		int maxDist = 0, minNode = INF, sameDist = 0;			// 최대 거리, 최대 거리를 가진 노드 중 가장 작은 인덱스, 같은 거리를 가진 노드의 수
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(START, cost[START]));					// 시작 노드와 시작 노드의 비용 입력, 다익스트라 알고리즘 실행
		
		while(!pq.isEmpty()){
			int current = pq.poll().node;
			
			for(Node next : barn[current]){
				if(cost[next.node] > next.val + cost[current]){
					cost[next.node] = next.val + cost[current];
					
					pq.offer(new Node(next.node, cost[next.node]));
				}
				
				if(cost[next.node] != INF){										// 다음 노드의 비용이 INF가 아닐 때
					maxDist = Math.max(maxDist, cost[next.node]);	// 최대 비용을 구함
				}
			}
		}
		
		for(int i = 2; i < N + 1; i++){
			if(maxDist == cost[i]){												// 1에서 해당 노드(i)까지 비용중 최대 비용과 같은 것
				minNode = Math.min(minNode, i);								// 그 중 최소 노드(i)의 값
				sameDist++;															// 같은 비용을 갖는 노드의 수
			}
		}
		
		// 결과값 한번에 출력
		System.out.println(minNode + SPACE + maxDist + SPACE + sameDist);
	}
	
	/**
	 * 
	 * 	@author minchoba
	 *	다음 도착지와 가는데 드는 비용을 담는 이너 클래스
	 *
	 */
	private static class Node implements Comparable<Node>{
		int node;
		int val;
		
		/**
		 * 
		 * @param node : 목적지
		 * @param val : 비용
		 * 
		 */
		public Node(int node, int val){
			this.node = node;
			this.val = val;
		}
		
		@Override
		public int compareTo(Node n) {	// 우선순위에 따라 큐 내부에서 정렬
			return (val < n.val) ? -1 : 1;
		}
	}
}
