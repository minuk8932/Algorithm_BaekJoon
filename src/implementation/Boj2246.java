package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2246 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Condo[] condos = new Condo[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			condos[i] = new Condo(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		br.close();

		int cnt = 0;

		for (int i = 0; i < N; i++) {
			int j = 0;

			for (; j < N; j++) {
				if (i == j) {
					continue;
				}

				if (condos[i].distance > condos[j].distance && condos[i].cost >= condos[j].cost
						|| condos[i].cost > condos[j].cost && condos[i].distance >= condos[j].distance) {
					break;
				}
			}

			
			if (j == N) {
				cnt++;
			}
		}

		System.out.println(cnt);
	}

	private static class Condo {
		public int distance;
		public int cost;

		public Condo(int distance, int cost) {
			this.distance = distance;
			this.cost = cost;
		}
	}
}