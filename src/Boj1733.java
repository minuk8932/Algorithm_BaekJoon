import java.io.*;
import java.util.*;

public class Boj1733 {
	private static ArrayList<Integer>[] connected;
	private static int[] A, B;
	private static int[] visit;
	private static int N;

	private static final String NEW_LINE = "\n";
	private static final int SIZE = 1_000_000;
	private static final int INF = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		connected = new ArrayList[N];
		A = new int[SIZE];
		B = new int[SIZE];
		for(int i = 0; i < N; i++) {
			connected[i] = new ArrayList<>();
		}

		Arrays.fill(A, -1);
		Arrays.fill(B, -1);

		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken()) - 1;
			int num2 = Integer.parseInt(st.nextToken()) - 1;

			connected[i].add(num1);
			if(num1 == num2) continue;
			connected[i].add(num2);
		}

		if(matching()) System.out.println(-1);
		else printer();
	}

	private static boolean matching() {
		int matched = 0;

		while(true) {
			bfs();

			int count = 0;
			for(int i = 0; i < N; i++) {
				if(A[i] != -1 || !dfs(i)) continue;
				count++;
			}

			if(count == 0) break;
			matched += count;
		}

		return matched < N;
	}

	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		visit = new int[SIZE];

		for(int i = 0; i < N; i++) {
			if(A[i] != -1) {
				visit[i] = INF;
				continue;
			}

			visit[i] = 0;
			q.offer(i);
		}

		while(!q.isEmpty()) {
			int current = q.poll();

			for(int next: connected[current]) {
				if(B[next] == -1 || visit[B[next]] != INF) continue;
				visit[B[next]] = visit[current] + 1;

				q.offer(B[next]);
			}
		}
	}

	private static boolean dfs(int current) {
		for(int next: connected[current]) {
			if(!(B[next] == -1 || (visit[B[next]] == visit[current] + 1 && dfs(B[next])))) continue;
			B[next] = current;
			A[current] = next;

			return true;
		}

		return false;
	}

	private static void printer() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for(int i = 0; i < N; i++) {
			bw.write(A[i] + 1 + NEW_LINE);
		}

		bw.flush();
	}
}

