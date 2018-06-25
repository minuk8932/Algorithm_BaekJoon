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
 *	백준 11724번 : 연결 요소의 개수
 *
 *	@see https://www.acmicpc.net/problem/11724
 *
 */
public class Boj11724 {
	private static final String SPACE = " ";

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		ArrayList<Integer>[] node = new ArrayList[N + 1];		// 인접리스트 선언

		for (int i = 1; i < N + 1; i++) {										// 인접리스트 초기화
			node[i] = new ArrayList<>();
		}

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine(), SPACE);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			node[x].add(y);
			node[y].add(x);
		}
		br.close();

		int cnt = 0;

		boolean[] isVisited = new boolean[N + 1];
		
		for (int i = 1; i < N + 1; i++) {								// 그래프에서 연결된 요소를 찾는 알고리즘
			if (!isVisited[i]) {
				
				cnt++;
				Queue<Integer> q = new LinkedList<>();
				q.offer(i);

				while (!q.isEmpty()) {
					int current = q.poll();

					for (final int next : node[current]) {		// 현재 i와 연결되어있는 다음 노드 중 아직 방문하지 않은 노드를 탐색해 나감
						if (!isVisited[next]) {
							isVisited[next] = true;

							q.offer(next);
						}
					}
				}
			}
		}

		System.out.println(cnt);										// 결과값 출력
	}
}
