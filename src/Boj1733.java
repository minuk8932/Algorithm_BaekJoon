import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1733 {
	private static ArrayList<Uniform>[] connected;
	private static int[] player;

	private static final String NEW_LINE = "\n";
	private static final int SIZE = 1_000_000;

	private static class Uniform {
		int node;
		int idx;

		public Uniform(int node, int idx) {
			this.node = node;
			this.idx = idx;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		connected = new ArrayList[SIZE];
		player = new int[SIZE];
		for(int i = 0; i < SIZE; i++) {
			connected[i] = new ArrayList<>();
			player[i] = -1;
		}

		HashSet<Integer> shirts = new HashSet<>();

		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken()) - 1;
			int num2 = Integer.parseInt(st.nextToken()) - 1;

			connected[num2].add(new Uniform(num1, i));
			connected[num1].add(new Uniform(num2, i));
			shirts.add(num1);
			shirts.add(num2);
		}

		System.out.println(shirts.size() > N ? -1: matching(shirts));
	}

	private static String matching(HashSet<Integer> set) {
		boolean[] visit = new boolean[SIZE];
		Queue<Uniform> q = new LinkedList<>();

		for(int i: set) {
			if(visit[i]) continue;
			boolean flag = false;
			int index = 0;

			for(Uniform start: connected[i]) {
				if(i != start.node) continue;
				flag = true;
				index = start.idx;
				break;
			}

			if(!flag) continue;
			q.offer(new Uniform(i, index));
			player[index] = i;
			visit[i] = true;
		}

		while(!q.isEmpty()) {
			Uniform current = q.poll();

			for(Uniform next: connected[current.node]) {
				if(visit[next.node]) continue;
				visit[next.node] = true;

				player[next.idx] = next.node;
				q.offer(new Uniform(next.node, next.idx));
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int p: player) {
			if(p == -1) continue;
			sb.append(p + 1).append(NEW_LINE);
		}

		return sb.toString();
	}
}

