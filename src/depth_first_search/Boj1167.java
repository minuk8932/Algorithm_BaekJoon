package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1167번: 트리의 지름
 *
 *	@see https://www.acmicpc.net.problem/1167/
 *
 */
public class Boj1167 {
	private static int max = 0;
	private static int maxIdx = 0;

	private static ArrayList<Node>[] tree = null;
	private static boolean[] isVisited = null;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		tree = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());

			while (true) {
				int to = Integer.parseInt(st.nextToken());
				if (to == -1) break;

				int val = Integer.parseInt(st.nextToken());
				tree[from].add(new Node(to, val));
			}
		}
		
	isVisited = new boolean[N + 1];
	dfs(1, 0);			// 임의의 정점 1부터 깊이 우선 탐색하여 가장 끝 지점을 찾아냄

	isVisited=new boolean[N+1];
	max = 0;
	dfs(maxIdx, 0);		// 찾아낸 가장 끝 점부터 깊이 우선 탐색으로 트리의 지름을 측정

	System.out.println(max);
}
	
	/**
	 * 간선 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Node {
		int edge;
		int cost;

		public Node(int edge, int cost) {
			this.edge = edge;
			this.cost = cost;
		}
	}
	
	/**
	 * 깊이 우선 탐색 메소드
	 * 
	 */
	private static void dfs(int start, int val) {
		isVisited[start] = true;

		for (Node n : tree[start]) {
			if (!isVisited[n.edge]) {
				dfs(n.edge, val + n.cost);	// 다음 정점을 들어가며 비용을 더하고 이어서 깊이 우선 탐색
			}
		}
		
		if(val > max) {		// 비용이 가장 클때의 값과, 노드를 구함
			max = val;
			maxIdx = start;
		}
	}
}
