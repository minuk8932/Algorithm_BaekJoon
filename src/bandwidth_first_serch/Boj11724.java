package bandwidth_first_serch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj11724 {
	private static final String SPACE = " ";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		ArrayList<Integer>[] node = new ArrayList[N + 1];

		for (int i = 1; i < N + 1; i++) {
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
		
		for (int i = 1; i < N + 1; i++) {
			if (!isVisited[i]) {
				
				cnt++;
				Queue<Integer> q = new LinkedList<>();
				q.offer(i);

				while (!q.isEmpty()) {
					int current = q.poll();

					for (final int next : node[current]) {
						if (!isVisited[next]) {
							isVisited[next] = true;

							q.offer(next);
						}
					}
				}
			}
		}

		System.out.println(cnt);
	}
}
