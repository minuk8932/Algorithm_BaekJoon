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
 *	백준 2637번: 장난감 조립
 *
 *	@see https://www.acmicpc.net/problem/2637/
 *
 */
public class Boj2637 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static ArrayList<Node>[] map = null;
	private static int[] size = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 계산
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		map = new ArrayList[N + 1];
		size = new int[N + 1];
		
		for(int i = 0; i < N + 1; i++) {
			map[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			map[from].add(new Node(to, cost));			// 조립의 역방향으로 값을 담아줌
			size[to]++;							// 다음 부분 완제품까지 조립 가능한 부품 개수 및 루트 노드를 구분지음
		}
		
		System.out.println(bfs(N));			// 너비 우선 탐색을 통한 결과 값 출력
	}
	
	/**
	 * 노드 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Node{
		int edge;
		int cost;
		
		public Node(int edge, int cost) {
			this.edge = edge;
			this.cost = cost;
		}
	}
	
	/**
	 * 너비 우선 탐색 메소드
	 * 
	 */
	private static String bfs(int finish) {
		StringBuilder sb = new StringBuilder();
		Queue<Integer> q = new LinkedList<>();
		
		int[][] ans = new int[finish + 1][finish + 1];	// 경로 별 부품의 합을 저장 할 배열
		for(int i = 1; i < finish + 1; i++) {
			if(size[i] == 0) {					// 루트 노드부터 큐에 담아줌
				q.offer(i);
				ans[i][i] = 1;
			}
		}
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(Node next: map[current]) {
				for(int i = 1; i < finish + 1; i++) {					// 현재 노드에 대해 이동 가능한 경로가 존재 할 때
					ans[next.edge][i] += ans[current][i] * next.cost;	// 각 경로에 값들을 하나씩 구해서 저장
				}
				
				size[next.edge]--;			// 조립 가능 경로 -1
				
				if(size[next.edge] == 0) q.offer(next.edge);		// 만약에 경로제거 후 루트 노드가 되는 부품이 있으면 큐에 저장
			}
		}
		
		for(int i = 1; i < finish + 1; i++) {			// 완제품을 만들 수 있는 부품을 찾고
			int res = ans[finish][i];
			
			if(res != 0) sb.append(i).append(SPACE).append(res).append(NEW_LINE);	// 있다면 조건에 맞추어 버퍼에 저장
		}
		
		return sb.toString();		// 버퍼의 값을 문자열로 변환해 한번에 반환 
	}
}
