package tree_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba 백준 1068번: 트리
 *
 * @see https://www.acmicpc.net/problem/1068/
 *
 */
public class Boj1068 {
	private static final int IS_ROOT = 0;
	private static final int INF = 51;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		ArrayList<Integer>[] graph = new ArrayList[INF];
		for (int i = 0; i < INF; i++) {
			graph[i] = new ArrayList<>();
		}

		int root = 0;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			int tmp = Integer.parseInt(st.nextToken()) + 1; // -1 입력을 제거

			graph[tmp].add(i); // 부모를 통한 자식 탐색을 위해 값을 반대로 입력
			if (tmp == IS_ROOT) // 해당 순서가 루트인 경우 루트의 현재 노드를 담아줌
				root = i;
		}

		int remove = Integer.parseInt(br.readLine()) + 1;

		for (int i = 1; i < N + 1; i++) {
			int size = graph[i].size();

			for (int j = 0; j < size; j++) {

				if (graph[i].get(j) == remove) {
					graph[i].remove(j); // 조건에 해당하는 자식노드를 즉, 값을 지움
					size--; // 지움으로써 줄어드는 사이즈를 변경
				}
			}
		}

		int size = graph[remove].size();

		for (int i = 0; i < size; i++) { // 조건에 해당하는 부모 노드 삭제
			graph[remove].remove(0);
		}

		System.out.println(root == remove ? 0 : search(graph, N, remove, root)); // 탐색 메소드를 통한 결과 출력, 만약 루트노드 삭제시 0 출력
	}
	
	/**
	 * 트리 탐색 메소드
	 * 
	 */
	private static int search(ArrayList<Integer>[] tree, int N, int r, int start) {
		int cnt = 0;

		Queue<Integer> q = new LinkedList<>();
		q.offer(start);

		while (!q.isEmpty()) {
			int current = q.poll();

			if (tree[current].isEmpty()) {		// 탐색을 하며 현재 노드에 값이 들어있지 않은 경우, 마지막 리프 노드이므로 +1
				cnt++;
			}

			for (int next : tree[current]) {
				if (next > 0 && next < N + 1 && next != r) {
					q.offer(next);
				}
			}
		}

		return cnt;		// 가장 끝의 리프노드 개수를 반환
	}
}
