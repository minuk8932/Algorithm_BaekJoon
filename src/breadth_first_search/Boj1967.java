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
 *	백준 1967번: 트리의 지름
 *
 *	@see https://www.acmicpc.net/problem/1967/
 *
 */
public class Boj1967 {
	private static final String SPACE = " ";
	private static int n = 0;
	
	private static int[][] cost = null;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		ArrayList<Integer>[] tree = new ArrayList[n + 1];
		for (int i = 1; i < n + 1; i++) {
			tree[i] = new ArrayList<>();
		}
		
		cost = new int[n + 1][n + 1];
		boolean[] hasIt = new boolean[n + 1];	// 출발 노드를 찾기위한 배열

		for (int i = 1; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int parent = Integer.parseInt(st.nextToken());
			int son = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			tree[parent].add(son);		// 양방향 연결
			tree[son].add(parent);
			
			cost[parent][son] = weight;		// 노드에 해당하는 비용을 양방향으로 담음
			cost[son][parent] = weight;
			hasIt[parent] = true;
		}

		int tmpStart = -1;

		for (int i = 1; i < n + 1; i++) {		// 임시 출발 노드(최 말단 자손노드 중 하나)를 찾음
			if (!hasIt[i]) {
				tmpStart = i;
				break;
			}
		}

		Answer res = bfs(tree, tmpStart);					// 너비 우선 탐색을 통한 임시 출발노드에서 최대비용이 드는 임의의 노드와 비용을 가져옴
		System.out.println(bfs(tree, res.idx).value - 1);	// 위에서 얻은 임의의 노드에서 최대 비용 - 1을 구함
	}
	
	/**
	 * 정답 저장 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Answer{
		int value;
		int idx;
		
		public Answer(int value, int idx) {
			this.value = value;
			this.idx = idx;
		}
	}
	
	/**
	 * 너비 우선 탐색 메소드
	 * 
	 */
	private static Answer bfs(ArrayList<Integer>[] map, int start) {
		int[] isVisited = new int[n + 1];
		int max = 0, maxStart = 0;
		
		isVisited[start] = 1;
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int next: map[current]) {
				if(isVisited[next] != 0) continue;
				isVisited[next] = cost[current][next] + isVisited[current];
					
				if(max < isVisited[next]) {		// 마지막까지 최대 비용과 해당 노드를 담아줌
					max = isVisited[next];
					maxStart = next;
				}
					
				q.offer(next);
			}
		}
		
		return new Answer(max, maxStart);		// 노드와 그때의 비용을 반환
	}
}
